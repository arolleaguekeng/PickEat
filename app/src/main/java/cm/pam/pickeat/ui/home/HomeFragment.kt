package cm.pam.pickeat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import cm.pam.pickeat.R
import cm.pam.pickeat.Service.adapters.CategoryAdapter2
import cm.pam.pickeat.Service.adapters.HomeCardAdapter
import cm.pam.pickeat.Service.adapters.MenuAdapter
import cm.pam.pickeat.Service.adapters.UserAdapter
import cm.pam.pickeat.databinding.FragmentHomeBinding
import cm.pam.pickeat.model.*
import cm.pam.pickeat.models.Category
import cm.pam.pickeat.models.Menu
import cm.pam.pickeat.models.Publication
import cm.pam.pickeat.models.User
import cm.pam.pickeat.ui.category.CategoryFragment
import cm.pam.pickeat.ui.menu_search.MenuSearchFragment


import java.util.*


class HomeFragment : Fragment() ,View.OnClickListener {

    lateinit var binding: FragmentHomeBinding
    private var categories = arrayListOf<Category>()
    var publications = arrayListOf<Publication>()
    var menus = arrayListOf<Menu>()

    var homeCardMeal = arrayListOf<HomeCardMeal>()
    var friends = arrayListOf<User>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.category.setOnClickListener(this)
        //Go to search page
        var topmenu = binding.topMenu.menu
        topmenu.children.elementAt(0).setOnMenuItemClickListener{ search() }


        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categories!!.layoutManager = mLayoutManager

        val mAdapter = CategoryAdapter2(categories){position ->  categoryClickListener(position)}
        binding.categories!!.adapter = mAdapter
        println(mAdapter.itemCount)
        binding.recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = MenuAdapter(menus)

        val Hadapter = HomeCardAdapter(homeCardMeal)
        binding.homecard!!.layoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homecard.adapter=HomeCardAdapter(homeCardMeal)

        var friendsAdapter = UserAdapter(friends)
        binding.friends.adapter = friendsAdapter

        return binding.root
    }

    //Find with searchView
    override fun onClick(p0: View?) {
        when(p0){
            binding.category->{
                var fragment = CategoryFragment()
                loadFragment(fragment)
            }
        }
    }

    //See all the categories
    private fun categoryClickListener(position: Int) {
        var fragment = MenuSearchFragment()
        var selectedCategory = categories[position]
        var arguments = Bundle()
        arguments.putString("title", selectedCategory.categoryName)
        fragment.arguments = arguments
        loadFragment(fragment)
    }

    //Switch to another fragment
    private fun loadFragment(fragment: Fragment) {
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.commit()
    }

    private fun search(): Boolean {
        var transaction = requireFragmentManager().beginTransaction()
        var fragment = MenuSearchFragment()
        fragment.arguments = arguments
        loadFragment(fragment)
        return true
    }

}