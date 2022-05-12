package cm.pam.pickeat.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cm.pam.pickeat.R
import cm.pam.pickeat.Service.adapters.CategoryAdapter
import cm.pam.pickeat.databinding.FragmentCategoryBinding
import cm.pam.pickeat.models.Category
import cm.pam.pickeat.ui.home.HomeFragment
import cm.pam.pickeat.ui.menu_search.MenuSearchFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isSearching: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var categories = arrayListOf<Category>(
    )
    lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val recycleView = binding.categoryList
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleView!!.layoutManager = mLayoutManager
        val mAdapter = CategoryAdapter(categories){position ->  categoryClickListener(position)}
        recycleView!!.adapter = mAdapter
        println(mAdapter.itemCount)

        var navigationIcon = binding.toolbar.setNavigationOnClickListener {
            var home = HomeFragment()
            loadFragment(home)
        }

        return binding.root
    }

    private fun categoryClickListener(position: Int){
        var fragment = MenuSearchFragment()
        var selectedCategory = categories[position]
        var arguments = Bundle()
        arguments.putString("title", selectedCategory.categoryName)
        fragment.arguments = arguments
        activity?.supportFragmentManager
            ?.beginTransaction()?.replace(R.id.container, fragment)
            ?.commit()
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            ?.addToBackStack("menu")
            ?.commit()
    }
}