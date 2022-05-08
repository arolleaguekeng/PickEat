package cm.pam.pickeat.ui.menu_search

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.Service.adapters.MenuAdapter
import cm.pam.pickeat.databinding.FragmentMenuSearchBinding
import cm.pam.pickeat.model.MealModel
import cm.pam.pickeat.model.MenuModel
import cm.pam.pickeat.model.PublicationModel
import cm.pam.pickeat.ui.home.HomeFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Menu2.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuSearchFragment : Fragment() {

    lateinit var chipGroup: ChipGroup
    var chipNumber = 0
    lateinit var searchView: SearchView
    lateinit var recycleView: RecyclerView
    lateinit var adapter: MenuAdapter
    lateinit var binding: FragmentMenuSearchBinding
    var filterOptions = ArrayList<String>()
    var publications = arrayListOf<PublicationModel>(
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 0.0, 2500.0),
        PublicationModel(MealModel("Traditional", "Eru", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tristique tellus a nibh pretium, in egestas quam ornare. Nullam ante lorem, fermentum non imperdiet ac, feugiat sollicitudin leo. Quisque elementum luctus erat, ac faucibus nisi finibus eget. Curabitur a interdum neque. Nam bibendum euismod nisl vel volutpat. Duis non nibh ut arcu congue congue ultricies eu nulla. Aenean dignissim enim eu sapien ullamcorper pharetra.", R.drawable.menu1), "Delicious Eru with Garry", R.drawable.menu1, 5.0, 2500.0)
    )
    var menus = arrayListOf<MenuModel>(
        MenuModel("ERU", "Traditionnal", "West", "Delicieux plat de eru tres armees aucune resistance ",
            R.drawable.category1, publications)
        , MenuModel("KOKI", "Traditionnal", "West", "le KOKI est bon quand ca chaufe",
            R.drawable.bro, publications)
        , MenuModel("Pommes", "Traditionnal", "Littoral", "la douceur des pommes du littoral dans un bouillions de viande sauté", R.drawable.pomme, publications)
        , MenuModel("Taro", "test", "test", "test", R.drawable.taro, publications)
        , MenuModel("Riz", "test", "test", "test", R.drawable.riz, publications)
    )
    var selectedMenu: List<MenuModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentMenuSearchBinding.inflate(inflater, container, false)
        var navigationIcon = binding.toolbar.setNavigationOnClickListener {
            var home = HomeFragment()
            loadFragment(home)
        }

        val recycleView: RecyclerView = binding.menuList
        recycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val mAdapter = MenuAdapter(menus)
        recycleView.adapter = mAdapter
        println(mAdapter.itemCount)

        var searchView = binding.toolbar.menu.children.elementAt(0).actionView as androidx.appcompat.widget.SearchView
        Toast.makeText(context, "${searchView.javaClass}", Toast.LENGTH_LONG*7).show()
        chipGroup = binding.chipGroup
        var filter = binding.toolbar.menu.children.elementAt(1).setOnMenuItemClickListener {
            chipNumber = 0
            filterOptions = ArrayList()
            useFilter() }

        //Search for a menu using SearchView
        searchView.setOnQueryTextListener(
            object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                selectedMenu = menus.filter { it.title.toLowerCase().contains(newText!!.toLowerCase()) }
                if(!selectedMenu.isNullOrEmpty())
                {
                    adapter = MenuAdapter(selectedMenu!! as ArrayList<MenuModel>)
                    recycleView.adapter = adapter
                    recycleView.refreshDrawableState()
                }
                return false
            }
        } )

        if(this.arguments!=null)
        {
            binding.toolbar.title = requireArguments().getString("title")
        }
        return binding.root
    }

    //Load another fragment inside the main activity
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        var transaction = activity?.supportFragmentManager
        transaction?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            ?.commit()
    }

    //Add chip when apply filter
    private fun addCriteria(text: String): Boolean{
        chipNumber++
        var chipChild: Chip = Chip(context)
        chipChild.text = text;
        chipChild.isCloseIconVisible = true
        chipChild.setOnClickListener{
            chipGroup.removeView(it)
            filterOptions.remove(chipChild.text)
        }
        if(chipGroup.childCount <=4 ){
            chipGroup.addView(chipChild)
        }
        else{
            chipChild = chipGroup.children.elementAt(4) as Chip
            chipChild.text = "+ ${chipNumber}"
            chipGroup.removeViewAt(4)
            chipGroup.addView(chipChild)
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun useFilter(): Boolean {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.fragment_filter, null)
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setView(mDialogView)
        mBuilder.setTitle("Filter options")

        val mAlertDialog = mBuilder.show()
        mDialogView.findViewById<Button>(R.id.apply).setOnClickListener {


            var countriesCities = mDialogView.findViewById<ConstraintLayout>(R.id.countries_cities)
            var cities = countriesCities.findViewById<ConstraintLayout>(R.id.cities)
            var countries = countriesCities.findViewById<ConstraintLayout>(R.id.countries)
            var flavors = mDialogView.findViewById<ConstraintLayout>(R.id.flavors)
            var nutrients = mDialogView.findViewById<ConstraintLayout>(R.id.nutrients)

            //Toast.makeText(context, "${flavors?.childCount}", Toast.LENGTH_SHORT).show()

            flavors.children.forEach { controlCheckBox(it) }
            nutrients.children.forEach { controlCheckBox(it) }
            countries.children.forEach { controlCheckBox(it) }
            cities.children.forEach { controlRadioGroup(it) }

            Toast.makeText(context, "${filterOptions.count()}", Toast.LENGTH_SHORT).show()

            for(i in 0..filterOptions.size-1){
                addCriteria(filterOptions[i])
            }
            mAlertDialog.dismiss()
        }

        mDialogView.findViewById<Button>(R.id.cancel).setOnClickListener {
            mAlertDialog.dismiss()
        }

        return true
    }

    private fun controlCheckBox(view: View){
        if(view is CheckBox){
            var checkBox = view as CheckBox
            if(checkBox.isChecked)
                filterOptions.add(checkBox.tag.toString())
        }
    }

    private fun controlRadioGroup(view: View){
        if(view is RadioGroup){
            var radioGroup = view as RadioGroup
            radioGroup.forEach {
                if((it as RadioButton).isChecked)
                    filterOptions.add(it.tag.toString())
            }
        }
    }
}