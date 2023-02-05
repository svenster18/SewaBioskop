package com.aufairfani.sewabioskop

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.aufairfani.sewabioskop.databinding.FragmentReservationBinding
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ReservationFragment : Fragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_GENRES = "extra_genres"
        const val EXTRA_CINEMA = "extra_cinema"
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_TAG = "TimePicker"
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


        binding.ibRentDate.setOnClickListener(this)
        binding.ibRentTime.setOnClickListener(this)
        binding.btnOrdernow.setOnClickListener(this)

        binding.spStudio.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val price = when (position) {
                    0 -> 80000
                    1 -> 125000
                    2 -> 200000
                    else -> 0
                }
                val nf: NumberFormat = NumberFormat.getInstance()
                binding.tvPriceorder.text = nf.format(price)
                val adm = 0.1 * price
                binding.tvAdmfee.text = nf.format(adm)
                var promo = 0.0
                if(price >= 125000) {
                    promo = 0.2 * price
                }
                binding.tvPromo.text = nf.format(promo)
                binding.tvTotalpayment.text = nf.format(price + adm - promo)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_rent_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.onDateSet = this
                val ft = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                datePickerFragment.show(ft, DATE_PICKER_TAG)
            }
            R.id.ib_rent_time -> {
                val timePickerFragment = TimePickerFragment()
                timePickerFragment.onTimeSet = this
                val ft = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                timePickerFragment.show(ft, TIME_PICKER_TAG)
            }
            R.id.btn_ordernow -> {
                if(binding.tvRentDate.text.equals("Rent Date")) {
                    Toast.makeText(requireActivity(), "Date must be chosen", Toast.LENGTH_SHORT).show()
                } else if(binding.tvRentTime.text.equals("Rent Time")) {
                    Toast.makeText(requireActivity(), "Time must be chosen", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Reservation Success", Toast.LENGTH_SHORT).show()
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // Set text dari textview once
        binding.tvRentDate.text = dateFormat.format(calendar.time)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        // Set text dari textview berdasarkan tag
        binding.tvRentTime.text = dateFormat.format(calendar.time)
    }
}