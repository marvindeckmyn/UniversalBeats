package be.howest.marvindeckmyn.beat

import android.app.DownloadManager
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.howest.marvindeckmyn.MainActivity
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.databinding.FragmentBeatBinding
import be.howest.marvindeckmyn.network.BuyBeat

class BeatFragment : Fragment() {

    lateinit var ACTIVITY: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as MainActivity
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentBeatBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val beat = BeatFragmentArgs.fromBundle(requireArguments()).selectedBeat
        val viewModelFactory = BeatViewModelFactory(beat, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(BeatViewModel::class.java)
        binding.viewModel = viewModel

        binding.beatName.setOnClickListener {
            viewModel.producer.value?.let { it1 -> viewModel.displayProducer(it1.get(0)) }
        }

        viewModel.navigateToSelectedProducer.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(BeatFragmentDirections.actionShowProducer(it))
                viewModel.displayProducerComplete()
            }
        })

        manageAudio(binding)
        val buyBtn = binding.buyBeat
        val audioUrl = binding.beatAudio

        buyBtn.setOnClickListener{
            val beatProducer = viewModel.producer.value?.get(0)?.id
            val beatName = viewModel.selectedBeat.value?.name
            val buyBeat = BuyBeat(beatProducer, beatName)
            viewModel.buyBeat(buyBeat)

            val request = DownloadManager.Request(Uri.parse(audioUrl.text.toString()))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setTitle("Download UNIVERSAL BEATS")
            request.setDescription("The beat is downloading...")

            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}")

            val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
            Toast.makeText(context, "Beat bought! The beat will download on your phone.", Toast.LENGTH_LONG).show()
        }


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun manageAudio(binding: FragmentBeatBinding) {
        val durationPlayed = binding.durationPlayed
        val durationTotal = binding.durationTotal
        val playPauseBtn = binding.playPause
        val seekBar = binding.durationBeat
        val audio = binding.beatAudio

        playPauseBtn.setImageResource(R.drawable.ic_pause)

        fun formattedTime(currentPosition: Int): String {
            val seconds = currentPosition % 60
            val minutes = currentPosition / 60
            val totalOut = "$minutes:$seconds"
            val totalNew = "$minutes:0$seconds"

            return if (seconds.toString().length == 1) {
                totalNew
            } else {
                totalOut
            }
        }

        if (ACTIVITY.mediaPlayer.isPlaying) {
            ACTIVITY.mediaPlayer.stop()
        }
        Handler().postDelayed({
            ACTIVITY.mediaPlayer = MediaPlayer().apply {
                setDataSource(audio.text.toString())
                prepare()
                start()
            }

            seekBar.max = (ACTIVITY.mediaPlayer.duration / 1000)

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        ACTIVITY.mediaPlayer.seekTo(progress * 1000)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null) {
                        ACTIVITY.mediaPlayer.seekTo(seekBar.progress * 1000)
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null) {
                        ACTIVITY.mediaPlayer.seekTo(seekBar.progress * 1000)
                    }
                }

            })

            val seekBarHandler = Handler()
            ACTIVITY.runOnUiThread(object: Runnable {
                override fun run() {
                    val currentPosition = (ACTIVITY.mediaPlayer.currentPosition / 1000)
                    seekBar.progress = currentPosition
                    durationPlayed.text = formattedTime(currentPosition)

                    if (seekBar.progress == seekBar.max) {
                        playPauseBtn.setImageResource(R.drawable.ic_play)
                    }

                    seekBarHandler.postDelayed(this, 1000)
                }
            })

            durationTotal.text = formattedTime(ACTIVITY.mediaPlayer.duration/1000)

            playPauseBtn.setOnClickListener {
                if (ACTIVITY.mediaPlayer.isPlaying) {
                    playPauseBtn.setImageResource(R.drawable.ic_play)
                    ACTIVITY.mediaPlayer.pause()
                } else {
                    playPauseBtn.setImageResource(R.drawable.ic_pause)
                    ACTIVITY.mediaPlayer.start()
                }
            }
        },1000)
    }
}