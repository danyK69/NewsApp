package com.codewithdan.newsdaily.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codewithdan.newsdaily.R
import com.codewithdan.newsdaily.WebActivity
import com.codewithdan.newsdaily.data.Article

class NewsAdapter (val context: Context,val articles: List<Article>):
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var newsTitle = itemView.findViewById<TextView>(R.id.news_title)
        var newsDescription = itemView.findViewById<TextView>(R.id.news_description)
        var newsImage = itemView.findViewById<ImageView>(R.id.news_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }
    }

}