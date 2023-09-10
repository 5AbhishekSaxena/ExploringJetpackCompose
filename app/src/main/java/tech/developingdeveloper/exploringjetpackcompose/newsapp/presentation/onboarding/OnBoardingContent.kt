package tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding.common.NewsButton
import tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding.common.NewsTextButton
import tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding.components.OnBoardingPage
import tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding.components.PageIndicator
import tech.developingdeveloper.exploringjetpackcompose.newsapp.ui.MediumPadding2
import tech.developingdeveloper.exploringjetpackcompose.newsapp.ui.NewsAppTheme
import tech.developingdeveloper.exploringjetpackcompose.newsapp.ui.PageIndicatorWidth

@Composable
fun OnBoardingContent(
    pages: List<OnBoardingPage>,
) {

    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size },
    )

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                pages.lastIndex -> listOf("Back", "Get Started")
                else -> listOf("Back", "Next")
            }
        }
    }

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {

            HorizontalPager(state = pagerState) { index ->
                OnBoardingPage(page = pages[index])
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding2),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PageIndicator(
                    pageSize = pages.size,
                    selectedPage = pagerState.currentPage,
                    modifier = Modifier.width(PageIndicatorWidth)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (buttonState.value[0].isNotEmpty()) {
                        NewsTextButton(
                            text = buttonState.value[0],
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                                }
                            }
                        )
                    }

                    NewsButton(
                        text = buttonState.value[1],
                        onClick = {
                            if (pagerState.currentPage == pagerState.pageCount - 1) {

                            } else {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun OnBoardingContentPreview() {
    NewsAppTheme {
        Surface {
            OnBoardingContent(pages)
        }
    }
}