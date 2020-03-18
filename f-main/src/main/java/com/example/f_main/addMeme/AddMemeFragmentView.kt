package com.example.f_main.addMeme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.f_main.R
import com.example.f_main.addMeme.di.AddMemeScreenConfigurator
import kotlinx.android.synthetic.main.fragment_feed.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.core.mvp.fragment.BaseRenderableFragmentView
import ru.surfstudio.android.core.mvp.presenter.CorePresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

class AddMemeFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bindModel: AddMemeBindModel
    @Inject
    lateinit var presenter: AddMemePresenter

    override fun getScreenName() = "FeedFragmentView"

    override fun createConfigurator() = AddMemeScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addmeme,container,false)
    }
}