package com.hyob.hyobtodoapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.gone
import com.hyob.hyobtodoapp.base.setNavigationIcon
import com.hyob.hyobtodoapp.base.setSupportActionBar
import com.hyob.hyobtodoapp.base.visible
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.presentation.adapter.TodoListAdapter
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoHolderViewModel
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoListViewModel
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoListViewModel.TodoFilter
import kotlinx.android.synthetic.main.fragment_todo_home.*
import kotlinx.android.synthetic.main.toolbar_todo_home.*
import kotlinx.coroutines.launch

class TodoHomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders
            .of(requireActivity(), TodoListViewModel.Factory(context!!))
            .get(TodoListViewModel::class.java)
    }

//    activity ktx 1.0.0 -beta01
//    appcompat 1.1.0- beta0
//    databinding

    private val todosAdapter = TodoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(toolbar)
        setNavigationIcon(R.drawable.ic_menu_white_24dp)

        todoRecyclerView.adapter = todosAdapter

        fabCreateTodo.setOnClickListener {
            findNavController().navigate(TodoHomeFragmentDirections.actionTodoCreateFragment())
        }

        with(viewModel) {

            showEmptyTodos()
                .observe(this@TodoHomeFragment, Observer {
                    todoRecyclerView.gone()
                })

            showTodos()
                .observe(this@TodoHomeFragment, Observer {
                    todoRecyclerView.visible()
                    todosAdapter.run {
                        submitList(it)
                    }
                })

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                true
            }
            R.id.action_todo_filter -> {
                showTodoFilterPopupMenu()
                true
            }
            R.id.action_todo_etc -> {
                showTodoEtcPopupMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun showTodoFilterPopupMenu() {
        PopupMenu(context, activity?.findViewById(R.id.action_todo_filter)).apply {
            menuInflater.inflate(R.menu.menu_todo_filter, menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_todo_all -> {
                        viewModel.updateTodoFilter(TodoFilter.ALL)
                    }
                    R.id.action_todo_active -> {
                        viewModel.updateTodoFilter(TodoFilter.ACTIVE)
                    }
                    R.id.action_todo_complete -> {
                        viewModel.updateTodoFilter(TodoFilter.COMPLETE)
                    }
                }
                true
            }
        }.show()
    }

    private fun showTodoEtcPopupMenu() {
        PopupMenu(context, activity?.findViewById(R.id.action_todo_etc)).apply {
            menuInflater.inflate(R.menu.menu_todo_home_etc, menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_todo_clear_complete -> {

                    }
                    R.id.action_todo_refresh -> {

                    }
                }
                true
            }
        }.show()
    }

}
