/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.common.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.pinaki.desipe.R

abstract class BaseBottomSheetDialogFragment<B : ViewBinding> : BottomSheetDialogFragment(),
    DialogInterface.OnShowListener {

    private lateinit var binding: B

    //region callback methods (abstract and open)
    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun getBinding(inflater: LayoutInflater, layout: Int, parent: ViewGroup?): B

    protected abstract fun injectDependencies()

    protected abstract fun initializeView()

    protected abstract fun observeDataAndActions()

    protected open fun loadData() {

    }

    open fun makeTopCornersRounded() = false

    open fun onDialogCancel() {

    }
    //endregion

    //region lifecycle methods
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        with(dialog) {
            setOnShowListener(this@BaseBottomSheetDialogFragment)
            setOnDismissListener { onDialogCancel() }
        }

        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onDialogCancel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater = inflater, layout = getLayout(), parent = container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        loadData()
        observeDataAndActions()
    }

    override fun onShow(iDialog: DialogInterface?) {
        val contentView =
            dialog!!.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

        val bottomSheetBehavior = BottomSheetBehavior.from(contentView)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    onDialogCancel()
                }
            }

        })

        if (makeTopCornersRounded()) {
            contentView.setBackgroundResource(R.drawable.bg_top_rouned_corners_16dp)
        }
    }
    //endregion

    //region utility methods
    fun getBindingInstance(): B {
        if (!::binding.isInitialized)
            throw IllegalStateException("Attempt to get binding instance before creating it")

        return binding
    }

    //endregion
}