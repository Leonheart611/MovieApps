package com.mikaocto.movieapps.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.DialogTitle
import com.mikaocto.movieapps.R
import com.mikaocto.movieapps.databinding.ErrorDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showErrorDialog(cause: String, handler: () -> Unit) {
        this.let { context ->
            val dialog = Dialog(context)
            with(dialog) {
                val bind = ErrorDialogBinding.inflate(layoutInflater)
                setContentView(bind.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                with(bind) {
                    tvErrorCause.text = cause
                    btnErrorAction.setOnClickListener {
                        handler.invoke()
                        dismiss()
                    }
                }
                show()
            }
        }
    }
}