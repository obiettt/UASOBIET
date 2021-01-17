package id.obiet.githubuser.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.obiet.githubuser.data.model.User
import id.obiet.githubuser.databinding.ActivityMainBinding
import id.obiet.githubuser.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)

        binding.apply {
            rcMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rcMain.setHasFixedSize(true)
            rcMain.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }

            etquery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(this, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

    }

    private fun searchUser() {
        binding.apply {
            val query = etquery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}