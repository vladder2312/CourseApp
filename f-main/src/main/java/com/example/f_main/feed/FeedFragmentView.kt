package com.example.f_main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.f_main.R
import com.example.f_main.feed.di.FeedScreenConfigurator
import kotlinx.android.synthetic.main.fragment_feed.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.core.mvp.loadstate.LoadStateInterface
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

/**
 * Вью экрана с мемами
 * */
class FeedFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bindModel: FeedBindModel

    @Inject
    lateinit var presenter: FeedPresenter
    private val adapter = EasyAdapter()
    private val feedController = FeedController(
            {
                bindModel.openMemeAction.accept(it)
            },
            {
                bindModel.shareMemeAction.accept(it)
            })
    private val searchListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?) = true

        override fun onQueryTextChange(query: String?): Boolean {
            if (query == null || query.trim().isEmpty()) {
                bindModel.hideMemesAction.accept()
            } else {
                bindModel.filterMemesAction.accept(query)
            }
            return true
        }
    }
    private val expandListener = object : MenuItem.OnActionExpandListener {

        override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
            bindModel.hideMemesAction.accept()
            return true
        }

        override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
            bindModel.showMemesAction.accept()
            return true
        }
    }

    override fun getScreenName() = "FeedFragmentView"

    override fun createConfigurator() = FeedScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        super.onActivityCreated(savedInstanceState, viewRecreated)

        initListeners()
        initResycler()
        bind()
    }

    private fun initListeners() {
        feed_toolbar.inflateMenu(R.menu.toolbar_feed_menu)
        feed_toolbar.menu.getItem(0).setOnActionExpandListener(expandListener)
        val search = feed_toolbar.menu.getItem(0).actionView as SearchView
        search.setOnQueryTextListener(searchListener)
        search.setOnCloseListener {
            bindModel.showMemesAction.accept()
            false
        }
        swipeRefresh.setOnRefreshListener {
            bindModel.refreshFeedAction.accept()
        }
    }

    private fun initResycler() {
        feedRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        feedRecycler.adapter = adapter
    }

    private fun bind() {
        bindModel.refreshFeedAction.accept()

        bindModel.placeholderState bindTo ::setLoadingState
        bindModel.memesState bindTo ::setMemes
    }

    private fun setMemes(memes: List<Meme>) {
        adapter.setData(memes, feedController)
        swipeRefresh.isRefreshing = false
    }

    private fun setLoadingState(state: LoadStateInterface) {
        placeholder.render(state)
    }
}