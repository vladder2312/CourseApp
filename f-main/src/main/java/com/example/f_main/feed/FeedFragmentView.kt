package com.example.f_main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.f_main.R
import com.example.f_main.feed.di.FeedController
import com.example.f_main.feed.di.FeedScreenConfigurator
import kotlinx.android.synthetic.main.fragment_feed.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

@PerScreen
class FeedFragmentView : BaseRxFragmentView(){

    @Inject
    lateinit var bindModel: FeedBindModel
    @Inject
    lateinit var presenter: FeedPresenter
    private val adapter = EasyAdapter()
    private val feedController = FeedController()

    override fun getScreenName() = "FeedFragmentView"

    override fun createConfigurator() = FeedScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        super.onActivityCreated(savedInstanceState, viewRecreated)

        initResycler()
        bind()
    }

    private fun bind(){
        bindModel.memesState bindTo ::setMemes
    }

    private fun initResycler(){
        feedRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        feedRecycler.adapter = adapter
    }

    private fun setMemes(memes : List<Meme>){
        adapter.setData(memes, feedController)
    }
}