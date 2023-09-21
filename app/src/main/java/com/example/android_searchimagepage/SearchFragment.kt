package com.example.android_searchimagepage

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_searchimagepage.Client.apiService
import com.example.android_searchimagepage.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.security.auth.callback.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mContext : Context
    private lateinit var adapter: CustomAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val dataList = mutableListOf<SearchData>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(mContext, 2)

        adapter = CustomAdapter(mContext)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        binding.searchButton.setOnClickListener {
            val searchWord = binding.searchEditText.text.toString()
            if(searchWord.isNotEmpty()) {
                searchImageResult(searchWord)
                dataList.clear()
            }
            else {
                Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchImageResult(query : String) {
        apiService.imageSearch(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : retrofit2.Callback<SearchDataModel?> {
                override fun onResponse(call: Call<SearchDataModel?>, response: Response<SearchDataModel?>) {
                    response.body()?.metaData?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.siteName
                                val datetime = changeDateFormat(document.datetime)
                                val url = document.thumbnailUrl
                                dataList.add(SearchData(url, title, datetime))
                                Log.d("DataList", "Item: $dataList")
                            }
                        }
                    }
                    adapter.searchItem = dataList
                    if(adapter.searchItem.isNotEmpty()) {
                        adapter.searchItem = dataList
                    }
                    else {
                        adapter.searchItem.clear()
                        adapter.searchItem = dataList
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SearchDataModel?>, t: Throwable) {
                }
            })
    }
    private fun changeDateFormat(datetime : String) : String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date : Date = inputDateFormat.parse(datetime)

        return outputDateFormat.format(date)
    }
}