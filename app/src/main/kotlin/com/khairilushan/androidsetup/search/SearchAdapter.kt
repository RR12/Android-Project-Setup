package com.khairilushan.androidsetup.search

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.khairilushan.domain.model.Repo
import com.khairilushan.androidsetup.R
import kotlinx.android.synthetic.main.list_item_repo.view.*

/**
 * Created by khairil on 11/16/17.
 */
class SearchRepositoryAdapter: RecyclerView.Adapter<SearchRepositoryAdapter.ViewHolder>() {

    private var data = listOf<RepoItemViewModel>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viewModel: RepoItemViewModel) = with(itemView) {
            Glide.with(itemView.context).load(viewModel.imageUrl).into(imageView)
            repoNameTextView.text = viewModel.repoName
            ownerNameTextView.text = viewModel.ownerName
            descriptionTextView.text = viewModel.description
        }
    }

    fun updateItems(newItems: List<RepoItemViewModel>) {
        val diffResult = DiffUtil.calculateDiff(RepoDiffCallback(data, newItems))
        data = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}

data class RepoItemViewModel(
        val imageUrl: String,
        val repoName: String,
        val ownerName: String,
        val description: String?
) {
    companion object {
        fun fromRepo(repo: Repo): RepoItemViewModel {
            return RepoItemViewModel(
                    imageUrl = repo.ownerAvatarUrl,
                    repoName = repo.fullName,
                    ownerName = repo.ownerName,
                    description = repo.description
            )
        }
    }
}

class RepoDiffCallback(private val mOldList: List<RepoItemViewModel>,
                       private val mNewList: List<RepoItemViewModel>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].repoName == mNewList[newItemPosition].repoName
    }

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].description == mNewList[newItemPosition].description
    }
}
