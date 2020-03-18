package com.example.f_main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f_main.R
import com.example.f_main.feed.di.FeedScreenConfigurator
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject

class FeedFragmentView : BaseRxFragmentView(){

    @Inject
    lateinit var bindModel: FeedBindModel
    @Inject
    lateinit var presenter: com.example.f_main.feed.FeedPresenter

    override fun getScreenName() = "FeedFragmentView"

    override fun createConfigurator() = FeedScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed,container,false)
    }
}