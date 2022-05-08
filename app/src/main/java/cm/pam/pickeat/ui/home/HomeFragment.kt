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
import cm.pam.pickeat.ui.category.CategoryFragment
import cm.pam.pickeat.ui.menu_search.MenuSearchFragment


import java.util.*


class HomeFragment : Fragment() ,View.OnClickListener {

    lateinit var binding: FragmentHomeBinding
    private var categories = arrayListOf<CategoryModel>(
        CategoryModel("Cameroonian", R.drawable.category_1),
        CategoryModel("Traditional Cameroonian", R.drawable.category_2),
        CategoryModel("Fast-Fod", R.drawable.category_3),
        CategoryModel("Asiatic Meal", R.drawable.category_4),
        CategoryModel("European Meal", R.drawable.category_5),
        CategoryModel("English Meal", R.drawable.category_6),
        CategoryModel("Drink", R.drawable.category_7),
        CategoryModel("Cake", R.drawable.category_8)
    )
    var publications = arrayListOf<PublicationModel>(
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.",
            R.drawable.bro), "Delicious Eru with Garry", R.drawable.category_3, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.",
            R.drawable.category_8
        ), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.",
            R.drawable.publish1
        ), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0)
    )
    var menus = arrayListOf<MenuModel>(
        MenuModel("ERU", "Traditionnal", "Sud West", "Le plat ci va te depassé lobstacle va te taclé", R.drawable.img, publications)
        , MenuModel("KOKI", "Traditionnal", "West", "le koki est bon quand ca chaufe",
            R.drawable.pommes_pilees, publications)
        , MenuModel("Pommes", "Taditionnal", "littoral", "délicieux pomme sauté pilé qui rechaufe le coeur", R.drawable.pomme, publications)
        , MenuModel("Taro", "Traditionnal", "Center", "les obstacles vont te depassé aujourd'huit", R.drawable.taro, publications)
        , MenuModel("Riz", "Chinois", "Asie", "les chinois creé nous on consome", R.drawable.riz, publications)
    )

    var homeCardMeal = arrayListOf<HomeCardMeal>(
        HomeCardMeal( "loic","bafang","Gateaux au chocola naper de greme blache ",R.drawable.cakechoco),
        HomeCardMeal( "brice","douala","Gateaux  ",R.drawable.steak),
        HomeCardMeal( "loic","bafang","Gateaux au chocola naper de greme blache ",R.drawable.cakechoco),
        HomeCardMeal( "brice","douala","Gateaux  ",R.drawable.steak),
        HomeCardMeal( "loic","bafang","Gateaux au chocola naper de greme blache ",R.drawable.takos),
        HomeCardMeal( "brice","douala","Gateaux  ",R.drawable.category_5),

        )

    var address = AddressModel("Douala", "Logbessou")
    var friends = arrayListOf<UserModel>(
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_7),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_3),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_1),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_7),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_3),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_1),
        UserModel("677777777", Date(), "Test", ByteArray(2), address, R.drawable.category_7),
    );

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

        //Action on searching from home searchView


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
        arguments.putString("title", selectedCategory.title)
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