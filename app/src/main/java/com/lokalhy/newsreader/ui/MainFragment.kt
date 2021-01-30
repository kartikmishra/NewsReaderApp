package com.lokalhy.newsreader.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.*
import com.facebook.shimmer.ShimmerFrameLayout
import com.lokalhy.newsreader.NewsState
import com.lokalhy.newsreader.NewsVM
import com.lokalhy.newsreader.R
import com.lokalhy.newsreader.base.simpleController
import com.lokalhy.newsreader.epoxyview.errorScreenView
import com.lokalhy.newsreader.epoxyview.newsArticleView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : BaseMvRxFragment() {


    private val newsVM: NewsVM by activityViewModel()

    private val newsArticleController: EpoxyController by lazy { buildController() }

    lateinit var  swipeLayout:SwipeRefreshLayout
    lateinit var mShimmerViewContainer: ShimmerFrameLayout


//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }


    private fun buildController() = simpleController(newsVM) { it ->
        showNewsArticle(it)
    }


    override fun invalidate() {
        withState(newsVM) { state ->
            newsArticleController.requestModelBuild()
            when(state.newsData) {
                is Loading -> {
                    mShimmerViewContainer.startShimmer()
                    mShimmerViewContainer.visibility = View.VISIBLE
                }
                Uninitialized -> {}
                is Success -> {
                    swipeLayout.isRefreshing = false
                    mShimmerViewContainer.stopShimmer()
                    mShimmerViewContainer.visibility = View.GONE
                }
                is Fail -> {
                    swipeLayout.isRefreshing = false
                    mShimmerViewContainer.stopShimmer()
                    mShimmerViewContainer.visibility = View.GONE
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_epoxy = view.findViewById<EpoxyRecyclerView>(R.id.rv_epoxy)
        rv_epoxy.setController(newsArticleController)
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        swipeLayout.setOnRefreshListener {
            newsVM.fetchTrendingNews()
        }

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            // Check if user triggered a refresh:
            R.id.menu_refresh -> {

                // Signal SwipeRefreshLayout to start the progress indicator
                swipeLayout.isRefreshing = true

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                myUpdateOperation()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun myUpdateOperation() {
        swipeLayout.isRefreshing = false
    }


    private fun EpoxyController.showNewsArticle(newsState: NewsState) {
        when(newsState.newsData) {

            is Uninitialized -> {

            }

            is Loading -> {
            }

            is Success -> {
                var data = newsState.newsData.invoke().articles

                data.forEachIndexed { index, article ->
                    newsArticleView {
                        id(article.title)
                        title(article.title)
                        description(article.description)
                        source(article.source.name)
                        urlToImage(article.urlToImage)
                        publishedAt(convertTime(article.publishedAt))
                        onClick {
                            newsVM.setNewsArticle(article)
                            findNavController().navigate(R.id.action_mainFragment_to_newsDetailFragment)
                        }
                    }
                }
            }

            is Fail -> {
                errorScreenView {
                    id("errorScreen")
                    onTryAgainClick {
                        tryToRefetchData()
                    }
                }
            }
        }
    }

    private fun tryToRefetchData() {
        newsVM.fetchTrendingNews()
    }

    fun convertTime(time:String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        input.timeZone = TimeZone.getTimeZone("UTC")
        val output = SimpleDateFormat("dd/MM/YY HH:mm")

        var d: Date? = null
        try {
            d = input.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatted: String = output.format(d)
        Log.i("DATE", "" + formatted)
        return "$formatted"
    }
}


