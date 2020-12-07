package com.bankin.task.activities

import android.os.Build
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bankin.task.R
import com.bankin.task.categories.CategorySearchActivity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class CategoriesTestActivity {

    private lateinit var categoriesSearchActivity: CategorySearchActivity

    @Before
    fun setup() {
        categoriesSearchActivity =
            buildActivity(CategorySearchActivity::class.java)
                .create()
                .resume()
                .get()
    }

    @Test
    fun `validate recycler list shown`() {
        val repoListView =
            categoriesSearchActivity.findViewById<RecyclerView>(R.id.rvRepository)
        assertThat(repoListView).isNotNull()
        assertThat(repoListView.isVisible)
    }

    @Test
    fun `validate sort menu is displayed`() {
        categoriesSearchActivity =
            buildActivity(CategorySearchActivity::class.java)
                .create()
                .visible()
                .get()
        val menu = Shadows.shadowOf(categoriesSearchActivity).optionsMenu
        val menuItemSortByName = menu.findItem(R.id.action_sort_by_name)
        val menuItemSortByStars = menu.findItem(R.id.action_sort_by_stars)
        assertThat(menuItemSortByName.isVisible)
        assertThat(menuItemSortByStars.isVisible)
    }

}