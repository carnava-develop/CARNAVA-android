package com.carnava.android.home.presentation

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.carnava.android.R
import com.carnava.android.category.domain.models.CategoryModel
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.ui.BaseListAdapter
import com.carnava.android.core.ui.BaseViewHolder
import com.carnava.android.core.utils.IdentificationDiffUtilCallback
import com.carnava.android.databinding.ItemChildCategoryBinding

class CategoryAdapter(
    private val childCategoryClickListener: (CategoryModel) -> Unit
) : BaseListAdapter<CategoryModel, BaseViewHolder<CategoryModel>>(
    IdentificationDiffUtilCallback()
) {

    override fun getItemViewType(position: Int) = when (getItem(position).baseCategory) {
        null -> BASE_CATEGORY_VH
        else -> CHILD_CATEGORY_VH
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        BASE_CATEGORY_VH -> BaseCategoryViewHolder(parent)
        else -> ChildCategoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CategoryModel>, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BaseCategoryViewHolder(parent: ViewGroup) : BaseViewHolder<CategoryModel>(
        ImageView(parent.context).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                250.toDpInt(),
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    ) {
        override fun bind(item: CategoryModel) {
            Glide.with(ctx).load(item.image).into(itemView as ImageView)
        }
    }

    inner class ChildCategoryViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<CategoryModel>(R.layout.item_child_category, parent) {

        private lateinit var currentItem: CategoryModel

        private val binding = ItemChildCategoryBinding.bind(itemView)

        init {
            itemView.setOnClickListener { childCategoryClickListener.invoke(currentItem) }
        }

        override fun bind(item: CategoryModel) {
            with(binding) {
                currentItem = item
                titleItemChildCategory.text = item.title
            }
        }
    }

    companion object {
        private const val BASE_CATEGORY_VH = 0
        private const val CHILD_CATEGORY_VH = 1
    }
}