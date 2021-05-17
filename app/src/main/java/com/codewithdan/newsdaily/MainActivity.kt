package com.codewithdan.newsdaily


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithdan.newsdaily.adapters.NewsAdapter
import com.codewithdan.newsdaily.api.NewsService
import com.codewithdan.newsdaily.data.Article
import com.codewithdan.newsdaily.data.News
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()

//    private var pageNum = 1
//    private var totalResults = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(this@MainActivity,articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

//        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
//        layoutManager.setPagerMode(true)
//        layoutManager.setPagerFlingVelocity(3000)
//        layoutManager.setItemChangedListener(object: StackLayoutManager.ItemChangedListener{
//            override fun onItemChanged(position: Int) {
//                if(totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5){
//                    pageNum++
//                    getNews()
//                }
//            }
//
//        })
        getNews()

    }

    private fun getNews() {
        val news =NewsService.newsInstance.getHeadlines("tesla",1)
        news.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Response","Error in fetching news",t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if( news !=null){
                    Log.d("Response", news.toString())
//                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }
}