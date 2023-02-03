package com.aufairfani.sewabioskop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aufairfani.sewabioskop.databinding.FragmentReservationBinding
import com.bumptech.glide.Glide
class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_GENRES = "extra_genres"
        const val EXTRA_CINEMA = "extra_cinema"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            var image = arguments?.getString(EXTRA_IMAGE)
            var title = arguments?.getString(EXTRA_TITLE)
            var genres = arguments?.getString(EXTRA_GENRES)
            var cinema = arguments?.getString(EXTRA_CINEMA)
            Glide.with(requireActivity())
                .load(image)
                .into(binding.ivMovieOrder)
            binding.tvTitleOrder.text = title
            binding.tvGenreOrder.text = genres
            binding.tvCinema.text = cinema
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}