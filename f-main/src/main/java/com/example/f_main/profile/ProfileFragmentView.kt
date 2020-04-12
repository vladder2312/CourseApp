package com.example.f_main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.f_main.R
import com.example.f_main.feed.FeedBindModel
import com.example.f_main.feed.FeedController
import com.example.f_main.feed.FeedPresenter
import com.example.f_main.profile.di.ProfileScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_addmeme.*
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.standard.domain.feed.Meme
import javax.inject.Inject

/**
 * Вью экрана профиля
 * */
class ProfileFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bindModel: ProfileBindModel

    @Inject
    lateinit var presenter: ProfilePresenter

    val adapter = EasyAdapter()
    private val feedController = FeedController(
            {
                bindModel.openMemeAction.accept(it)
            },
            {
                bindModel.shareMemeAction.accept(it)
            })

    override fun getScreenName() = "ProfileFragmentView"

    override fun createConfigurator() = ProfileScreenConfigurator(Bundle.EMPTY)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        super.onActivityCreated(savedInstanceState, viewRecreated)

        toolbarProfile.inflateMenu(R.menu.profile_menu)
        initResycler()
        initListeners()
        bind()
    }

    private fun initResycler() {
        profile_memes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        profile_memes.adapter = adapter
    }

    private fun initListeners() {
        toolbarProfile.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_about -> {
                    true
                }
                R.id.action_exit -> {
                    bindModel.openExitDialog.accept()
                    true
                }
                else -> false
            }
        }
        profile_swipe_refresh.setOnRefreshListener {
            bindModel.refreshAction.accept()
        }
    }

    private fun bind() {
        bindModel.refreshAction.accept()

        bindModel.userDataState bindTo ::setUserData
        bindModel.memesState bindTo ::setMemes
    }

    private fun setUserData(data: Pair<String, String>) {
        profile_nickname.text = data.first
        profile_description.text = data.second
    }

    private fun setMemes(memes: List<Meme>) {
        adapter.setData(memes, feedController)
        profile_swipe_refresh.isRefreshing = false
    }
}