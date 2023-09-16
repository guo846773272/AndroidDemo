package com.example.basicviewsactivitydemo

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviewsactivitydemo.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrinterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

object SharedPrinter {
    val bluetoothDeviceModelMAry = mutableListOf<BluetoothDeviceModel>()
    var selectedBluetoothDeviceModel: BluetoothDeviceModel? = null
}

data class BluetoothDeviceModel(
    val device: BluetoothDevice,
    var selected: Boolean,
) {}

class PrinterAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PrinterViewHolder(itemView: View, adapter: PrinterAdapter): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imgView: ImageView = itemView.findViewById(R.id.imgView)
        var model: BluetoothDeviceModel? = null
            set(value) {
                value?.let {
                    textView.text = "---${it.device.name}---${it.device.address}---"
                    if (value.selected) {
                        textView.text = "---${it.device.name}---${it.device.address}---selected"
                        imgView.setBackgroundColor(Color.RED)
                        imgView.setBackgroundResource(R.mipmap.selected)
                    } else {
                        textView.text = "---${it.device.name}---${it.device.address}---"
                        imgView.setBackgroundColor(Color.BLUE)
                        imgView.setBackgroundResource(R.mipmap.unselected)
                    }
                }
//                val name = model.name
//                textView.text = model.name
                field = value
            }
        init {
            itemView.setOnClickListener {
                model?.let { model ->
                    SharedPrinter.bluetoothDeviceModelMAry.forEach {
                        it.selected = false
                    }
                    model.selected = true
                    SharedPrinter.selectedBluetoothDeviceModel = model
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.printer_cell, parent, false)
        return PrinterViewHolder(cell, this)
    }

    override fun getItemCount(): Int {
        return SharedPrinter.bluetoothDeviceModelMAry.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PrinterViewHolder) {
            holder.model = SharedPrinter.bluetoothDeviceModelMAry[position]
        }
    }

}

class PrinterFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//            ?: run {
//
//        }

        if (!myBluetoothAdapter.isEnabled) {

        }

        val pairedDevices: Set<BluetoothDevice> = myBluetoothAdapter.bondedDevices
        if (pairedDevices.size <= 0) {

        }

        for (device in pairedDevices) {
            val bondedDeviceModel = BluetoothDeviceModel(device = device, selected = false)
            SharedPrinter.bluetoothDeviceModelMAry.add(bondedDeviceModel)
        }

        binding.recyclerView.adapter = PrinterAdapter()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrinterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrinterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}