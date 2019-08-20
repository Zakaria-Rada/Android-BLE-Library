/*
 * Copyright (c) 2018, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.ble.response

import android.bluetooth.BluetoothDevice
import android.os.Parcel
import android.os.Parcelable

import androidx.annotation.IntRange

import no.nordicsemi.android.ble.callback.MtuCallback

class MtuResult// Parcelable
protected constructor(`in`: Parcel) : MtuCallback, Parcelable {
    var bluetoothDevice: BluetoothDevice? = null
        private set
    /**
     * Returns the agreed MTU. The maximum packet size is 3 bytes less then MTU.
     *
     * @return The MTU.
     */
    @IntRange(from = 23, to = 517)
    @get:IntRange(from = 23, to = 517)
    var mtu: Int = 23
        private set

    init {
        bluetoothDevice = `in`.readParcelable(BluetoothDevice::class.java.classLoader)
        mtu = `in`.readInt()
    }

    override fun onMtuChanged(
        device: BluetoothDevice,
        @IntRange(from = 23, to = 517) mtu: Int
    ) {
        this.bluetoothDevice = device
        this.mtu = mtu
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(bluetoothDevice, flags)
        dest.writeInt(mtu)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MtuResult> = object : Parcelable.Creator<MtuResult> {
            override fun createFromParcel(`in`: Parcel): MtuResult {
                return MtuResult(`in`)
            }

            override fun newArray(size: Int): Array<MtuResult?> {
                return arrayOfNulls(size)
            }
        }
    }
}
