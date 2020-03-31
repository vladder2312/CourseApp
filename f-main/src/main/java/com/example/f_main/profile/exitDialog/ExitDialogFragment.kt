package com.example.f_main.profile.exitDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f_main.R
import com.example.f_main.profile.ProfilePresenter
import kotlinx.android.synthetic.main.dialog_exit.*
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.surfstudio.android.mvp.dialog.simple.CoreSimpleDialogFragment
import javax.inject.Inject

/**
 * Вью диалога выхода
 * */
class ExitDialogFragment : CoreSimpleDialogFragment() {

    @Inject
    lateinit var presenter: ProfilePresenter

    override fun getName() = "Profile Dialog Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScreenComponent(ExitDialogComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.dialog_exit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        exit_cancel.setOnClickListener {
            dismiss()
        }
        exit_exit.setOnClickListener {
            presenter.exit()
            dismiss()
        }
    }
}