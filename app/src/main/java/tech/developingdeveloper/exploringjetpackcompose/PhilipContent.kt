package tech.developingdeveloper.exploringjetpackcompose

import android.view.MotionEvent
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.Typography
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

const val PHILIP_CONTENT_ROUTE = "philip_content"

@Composable
fun PhilipContent() {
//        MyCard()
//        TextStylePlayground()
//        StatePlayground()
//        WidgetPlayground()
//        ListPlayground()
//        ExampleConstraintLayout()
//        SideEffectExample()
//        AnimationPlayground()
//        CircularProgressBarPlayground()
//        MusicKnobPlayground()
    SimpleTimerPlayground()
}


@Composable
fun MyCard() {
    val cardImage = CardImage(
        title = "Sample nature image",
        painter = painterResource(id = R.drawable.sample_image),
        contentDescription = "This is content Description for the image"
    )
    Box(
        modifier = Modifier
            .fillMaxSize(0.5f)
            .padding(16.dp)
    ) {
        ImageCard(cardImage = cardImage)
    }
}

@Composable
fun ImageCard(
    cardImage: CardImage,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = cardImage.painter,
                contentDescription = cardImage.contentDescription,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = cardImage.title,
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TextStylePlayground() {

    val inputText: AnnotatedString = buildCustomAnnotatedString("Jetpack Compose")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF101010))
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = inputText,
        color = Color.White,
        fontSize = 30.sp,
        style = Typography.body2,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.combine(
            listOf(
                TextDecoration.LineThrough,
                TextDecoration.Underline
            )
        )
    )
}

fun buildCustomAnnotatedString(text: String): AnnotatedString {
    val words = text.split(" ")
    val outputStringBuilder = AnnotatedString.Builder()

    val firstLetterStyle = SpanStyle(color = Color.Green, fontSize = 50.sp)

    words.forEachIndexed { index, s ->
        outputStringBuilder.withStyle(firstLetterStyle) { append(s[0]) }
        outputStringBuilder.append(s.substring(1))

        if (index != words.size - 1)
            outputStringBuilder.append(" ")
    }

    return outputStringBuilder.toAnnotatedString()
}

@Composable
fun StatePlayground() {
    val color = remember { mutableStateOf(Color.Yellow) }

    Column(modifier = Modifier.fillMaxSize()) {
        ColorBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            onClick = {
                color.value = getRandomColor()
            })

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(color = color.value)
        )
    }
}

@Composable
private fun ColorBox(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Box(modifier = modifier
        .background(color = Color.Yellow)
        .clickable { onClick() }
    )

}

fun getRandomColor(): Color {
    return Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
}

@Composable
@ExperimentalComposeUiApi
fun WidgetPlayground() {

    val scaffoldState = rememberScaffoldState()
    var textFieldFieldState by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            NameTextField(
                text = textFieldFieldState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                keyboardActions = {
                    hideKeyboardAndShowSnackbar(
                        keyboardController = keyboardController,
                        scaffoldState = scaffoldState,
                        text = textFieldFieldState,
                        coroutineScope = coroutineScope
                    )
                },
                onValueChanged = {
                    textFieldFieldState = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                onClick = {
                    hideKeyboardAndShowSnackbar(
                        keyboardController = keyboardController,
                        scaffoldState = scaffoldState,
                        text = textFieldFieldState,
                        coroutineScope = coroutineScope
                    )
                }) {
                Text(text = "Click Me!", maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }

    }
}

@Composable
private fun NameTextField(
    text: String,
    modifier: Modifier = Modifier,
    keyboardActions: () -> Unit,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        value = text,
        label = {
            Text(text = "Enter your name")
        },
        onValueChange = {
            onValueChanged(it)
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            keyboardActions()
        }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier
    )
}

@ExperimentalComposeUiApi
fun hideKeyboardAndShowSnackbar(
    keyboardController: SoftwareKeyboardController?,
    scaffoldState: ScaffoldState,
    text: String,
    coroutineScope: CoroutineScope,
) {
    hideKeyboard(keyboardController)
    showSnackbar(coroutineScope, scaffoldState, text)
}

@ExperimentalComposeUiApi
private fun hideKeyboard(keyboardController: SoftwareKeyboardController?) {
    keyboardController?.hide()
}

private fun showSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    text: String,
) {
    coroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar("Hello $text")
    }
}

@Composable
fun ListPlayground() {
    // SimpleScrollableList()
    LazyScrollableList()
}

@Composable
fun SimpleScrollableList() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..50) {
            ListItemText(text = "Item $i")
        }
    }
}

@Composable
fun LazyScrollableList() {
//    LazyColumnWithFiftyItems()
    LazyColumnWithListOfStrings()
}

@Composable
private fun LazyColumnWithFiftyItems() {
    LazyColumn {
        items(count = 50) { index ->
            ListItemText(text = "Item $index")
        }
    }
}

@Composable
fun LazyColumnWithListOfStrings() {
    val words = "I am learning Jetpack Compose".split(" ")

    LazyColumn {
        items(words) { word ->
            ListItemText(text = word)

        }
    }
}

@Composable
fun ListItemText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
fun ConstraintLayoutPlayground() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("green_box")
        val redBox = createRefFor("red_box")
        val purpleBox = createRefFor("purple_box")

        val yellowBox = createRefFor("yellow_box")
        val blackBox = createRefFor("black_box")

        val topGuideline = createGuidelineFromTop(16.dp)
        val startGuideLine = createGuidelineFromStart(16.dp)

        constrain(greenBox) {
//            top.linkTo(parent.top)
            top.linkTo(topGuideline)
//            start.linkTo(parent.start)
            start.linkTo(startGuideLine)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top, 16.dp)
            start.linkTo(greenBox.end)

            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(purpleBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end, 20.dp)
            start.linkTo(redBox.end)
            end.linkTo(parent.end)

            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(yellowBox) {
            start.linkTo(parent.start)
            top.linkTo(greenBox.bottom)
            end.linkTo(blackBox.start)
            bottom.linkTo(parent.bottom)

            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(blackBox) {
            start.linkTo(yellowBox.end)
            top.linkTo(yellowBox.top)
            end.linkTo(yellowBox.end)
            bottom.linkTo(yellowBox.bottom)

            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(yellowBox, blackBox)
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("green_box")
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("red_box")
        )
        Box(
            modifier = Modifier
                .background(Color.Magenta)
                .layoutId("purple_box")
        )
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .layoutId("yellow_box")
        )
        Box(
            modifier = Modifier
                .background(Color.Black)
                .layoutId("black_box")
        )
    }
}

@Composable
fun SideEffectPlayground() {

//    DisposableSideEffectExample(onBackPressedDispatcher)
//    SideEffectOtherExample()
//    SideEffectProduceStateExample()
}

@Composable
private fun DisposableSideEffectExample(onBackPressedDispatcher: OnBackPressedDispatcher) {
    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do something
            }
        }
    }

    DisposableEffect(key1 = onBackPressedDispatcher) {
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        onDispose {
            onBackPressedCallback.remove() // free up
        }
    }

    Button(onClick = { }) {
        Text(text = "Click me!")
    }
}

// Note:
// Question: Are there any reasons for preferring the use of LaunchedEffect over rememberCoroutineScope()
//           inside composable functions?
// Answer: LaunchedEffect - It should be used when you want that some action must be taken when your
//                          composable is first launched. For example, when you want to request
//                          some data from your ViewModel or run some sort of animation...
//
//         rememberCoroutineScope - It is specific to store the Coroutine scope allowing the code
//                                  to launch some suspend function... imho, the only relation
//                                  between them is that you can also use a LaunchedEffect
//                                  to launch a coroutine...
//
// Question: Is it worth the effort to only create / remember a coroutine scope once per compose
//           tree, or should I just call rememberCoroutineScope() in each function where a coroutine
//           is actually launched?
// Answer: As you can see in the docs, rememberCoroutineScope will keep the reference of the
//         coroutine's scope in a specific point of the composition. Therefore, if a given
//         composable is removed from the recomposition, that coroutine will be cancelled
//         automatically.
//         For instance, you have the following composable calls A -> B -> C. If you remember the
//         coroutine scope in C and it is removed from the composition, the coroutine is
//         automatically cancelled. But if you remember from A, pass the scope through B and C,
//         use this scope in C, and then C is removed, the coroutine will continue running
//         (because it was remembered in A)...

@Composable
fun SideEffectOtherExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        var counter by remember { mutableStateOf(0) }

        if (counter % 5 == 0 && counter > 0) {
//            coroutineScope.launch {
//                scaffoldState.snackbarHostState.showSnackbar("Counter : $counter")
//            }

            // alternatively and preferred - refer notes to make the right choice

            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar("Counter : $counter")
            }
        }

        Button(onClick = { counter++ }) {
            Text(text = "Click Me!: $counter")
        }
    }
}

@Composable
fun SideEffectProduceStatePlayground() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        val counter = produceState(initialValue = 0) {
            delay(3000)
            value = 5
        }

        if (counter.value % 5 == 0 && counter.value > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar("Counter: ${counter.value}")
            }
        }

        Button(onClick = { }) {
            Text(text = "Click me: ${counter.value}")
        }
    }
}

@Composable
fun AnimationPlayground() {

    val initialBoxSize = 200.dp
    var sizeState by remember { mutableStateOf(initialBoxSize) }

    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec =
        tween(300, delayMillis = 200, easing = LinearEasing)
//        keyframes {
//            durationMillis = 5000
//            sizeState at 0 with LinearEasing
//            sizeState * 1.5f at 1000 with FastOutLinearInEasing
//            sizeState * 2f at 5000
//        } // same can be done using tween()

    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text(text = "Increase Size")
        }
    }
}

@Composable
fun CircularProgressBarPlayground() {

    var total by remember { mutableStateOf(100f) }
    var completed by remember { mutableStateOf(80f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressBar(total = total, completed = completed)
        Spacer(modifier = Modifier.height(200.dp))
        Row {
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Number: ")
                }
                append(completed.toString())
            })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Total: ")
                }
                append(total.toString())
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { total += 10 }) {
            Text(text = "Increase Total by 10")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                total = if (total - 10 <= 0) 0f else total - 10
                if (completed > total) completed = total
            },
            enabled = total > 0
        ) {
            Text(text = "Decrease Total by 10")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { total += 50 }) {
            Text(text = "Increase Total by 50")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                total = if (total - 50 < 0) 0f else total - 50
                if (completed > total) completed = total
            },
            enabled = total > 0
        ) {
            Text(text = "Decrease Total by 50")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { completed = if (completed + 10 > total) total else completed + 10 },
            enabled = completed < total
        ) {
            Text(text = "Increase Number by 10")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { completed = if (completed - 10 < 0) 0f else completed - 10 },
            enabled = completed > 0f
        ) {
            Text(text = "Decrease Number by 10")
        }
    }
}

@Composable
fun CircularProgressBar(
    total: Float,
    completed: Float,
    radius: Dp = 50.dp,
    textColor: Color = Color.Black,
    textFontSize: TextUnit = 28.sp,
    textFontWeight: FontWeight = FontWeight.SemiBold,
    strokeColor: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animationDurationMillis: Int = 1000,
    animationDelayMillis: Int = 0,
) {
    var isAnimationPlayed by remember { mutableStateOf(false) }

    val currentPercentage =
        animateFloatAsState(
            targetValue = if (isAnimationPlayed) {
                if (total > 0) (completed / total) * 100
                else 0f
            } else
                0f,
            animationSpec = tween(
                durationMillis = animationDurationMillis,
                delayMillis = animationDelayMillis
            )
        )

    LaunchedEffect(key1 = true) {
        isAnimationPlayed = true
    }

    val boxSize = radius * 2f

    Box(
        modifier = Modifier
            .size(boxSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier =
            Modifier.size(boxSize)
        ) {
            drawArc(
                color = strokeColor,
                startAngle = -90f,
                sweepAngle = 360 * (currentPercentage.value / 100),
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (currentPercentage.value).toInt().toString(),
            color = textColor,
            fontSize = textFontSize,
            fontWeight = textFontWeight
        )
    }

}

@ExperimentalComposeUiApi
@Composable
fun MusicKnobPlayground() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }

            val barCount = 20

            MusicKnob(modifier = Modifier.size(100.dp)) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )

        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit,
) {
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }

    var touchX by remember {
        mutableStateOf(0f)
    }

    var touchY by remember {
        mutableStateOf(0f)
    }

    var centerX by remember {
        mutableStateOf(0f)
    }

    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter {
                touchX = it.x
                touchY = it.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / Math.PI).toFloat()

                when (it.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE,
                    -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = calculateFixedAngle(angle, limitingAngle)
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }

                    else -> false
                }
            }
            .rotate(rotation)
    )
}

private fun calculateFixedAngle(angle: Float, limitingAngle: Float): Float {
    return if (angle in -180f..-limitingAngle)
        360f + angle
    else
        angle
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10,
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

@Composable
fun SimpleTimerPlayground() {
    Surface(
        color = Color(0xFF101010),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.Center) {
            SimpleTimer(
                totalTime = 100L * 1000L,
                handleColor = Color.Green,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFF37B900),
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun SimpleTimer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp,
    fontSize: TextUnit = 44.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textColor: Color = Color.White,
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0L && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.onSizeChanged {
            size = it
        }) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val centerOffset = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (Math.PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(centerOffset.x + a, centerOffset.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = textColor
        )

        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L)
                    Color.Green
                else
                    Color.Red
            )
        ) {
            Text(
                text = when {
                    isTimerRunning && currentTime >= 0L -> "Stop"
                    !isTimerRunning && currentTime >= 0L -> "Start"
                    else -> "Restart"
                }
            )
        }
    }
}