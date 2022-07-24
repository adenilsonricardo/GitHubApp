package com.example.githubrepositoryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.example.githubrepositoryapp.R
import com.example.githubrepositoryapp.core.createDialog
import com.example.githubrepositoryapp.core.createProgressDialog
import com.example.githubrepositoryapp.core.hideSoftKeyboard
import com.example.githubrepositoryapp.databinding.ActivityMainBinding
import com.example.githubrepositoryapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val dialog by lazy {createProgressDialog()}
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter()  }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter

        viewModel.repos.oberve(this) {
            when(it){
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let{viewModel.getRepoList(it)}
        binding.root.hideSoftKeyboard()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange: $newText")
        return false
    }

    companion object{
        private const val TAG = "TAG"
    }
}
