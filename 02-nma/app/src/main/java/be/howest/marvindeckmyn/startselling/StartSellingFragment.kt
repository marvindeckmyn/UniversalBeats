package be.howest.marvindeckmyn.startselling

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentStartsellingBinding
import be.howest.marvindeckmyn.network.Beats
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File

class StartSellingFragment : Fragment() {

    private lateinit var sellBeatCoverUri: Uri
    private lateinit var sellBeatCover: ImageView
    private lateinit var beatAudioUri: Uri
    private lateinit var beatAudioDetected: TextView

    companion object {
        val IMAGE_REQUEST_CODE = 100
        val AUDIO_REQUEST_CODE = 101
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentStartsellingBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_startselling, container, false
        )

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { StartSellingViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(StartSellingViewModel::class.java)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.beatCoverUpload.setOnClickListener {
            pickImageGallery()
        }

        binding.uploadBeatFileButton.setOnClickListener {
            pickAudio()
        }

        sellBeatCover = binding.beatCoverImage
        beatAudioDetected = binding.beatFileDetected

        val genreValues = arrayListOf<String>()
        val moodValues = arrayListOf<String>()

        Handler().postDelayed({
            if (viewModel != null) {
                for (genre in viewModel.genres.value!!)
                    genreValues.add(genre.name)
            }

            if (viewModel != null) {
                for (mood in viewModel.moods.value!!)
                    moodValues.add(mood.name)
            }

            val genreSpinnerAdapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, genreValues) }
            binding.genres.adapter = genreSpinnerAdapter

            val moodSpinnerAdapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, moodValues) }
            binding.moodUploadForm.adapter = moodSpinnerAdapter
        }, 1000)


        binding.uploadButton.setOnClickListener {
            val countBeats = viewModel?.beats?.value?.size

            val beatId = countBeats?.plus(1)
            val beatName = binding.beatNameUploadForm.text.toString()
            val beatProducerId = viewModel?.producer?.value?.get(0)?.id.toString()

            val byteArrayOutputStream = ByteArrayOutputStream()

            val bitmapCover = MediaStore.Images.Media.getBitmap(context?.contentResolver, sellBeatCoverUri)
            bitmapCover.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
            val coverInByte = byteArrayOutputStream.toByteArray()
            val beatCover = Base64.encodeToString(coverInByte, Base64.DEFAULT)

            val beatGenre = binding.genres.selectedItem.toString()
            val beatTag = binding.tagUploadForm.text.toString()
            val beatBpm = binding.bpmUploadForm.text.toString()
            val beatMood = binding.moodUploadForm.selectedItem.toString()
            val beatPrice = binding.priceUploadForm.text.toString()

            val audioInputStream = context?.contentResolver?.openInputStream(beatAudioUri)
            val audioInByte = audioInputStream?.readBytes()
            val beatAudio = Base64.encodeToString(audioInByte, Base64.DEFAULT)

            val beatToSell = beatId?.let { Beats(it, beatName, beatProducerId, beatCover, beatGenre, beatTag, beatBpm, beatMood, beatPrice, beatAudio) }


            if (beatToSell != null) {
                viewModel?.postBeat(beatToSell)
                Toast.makeText(context, "Beat successfully uploaded!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun pickAudio() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*"
        startActivityForResult(intent, AUDIO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                sellBeatCoverUri = data.data!!
            }
            sellBeatCover.setImageURI(data?.data)
        }

        if (requestCode == AUDIO_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                beatAudioUri = data.data!!
            }
            beatAudioDetected.text = "Beat audio uploaded!"
        }
    }


}