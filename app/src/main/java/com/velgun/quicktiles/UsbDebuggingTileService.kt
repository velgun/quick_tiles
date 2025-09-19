package com.velgun.quicktiles

import android.graphics.drawable.Icon
import android.service.quicksettings.Tile
import android.provider.Settings
import android.service.quicksettings.TileService

class UsbDebuggingTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile
        val isEnabled = isUsbDebuggingEnabled()
        tile.state = if (isEnabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.label = getString(R.string.usb_debugging)
        tile.icon = Icon.createWithResource(applicationContext, R.drawable.baseline_adb_24)
        tile.updateTile()
    }

    override fun onClick() {
        val isEnabled = isUsbDebuggingEnabled()
        if (isEnabled) {
            update(false)
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            update(true)
            qsTile.state = Tile.STATE_ACTIVE
        }
        qsTile.updateTile()
    }


    private fun isUsbDebuggingEnabled(): Boolean {
        return try {
            Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1
        } catch (_: Settings.SettingNotFoundException) {
            false
        }
    }

    private fun update(enabled: Boolean) {
        if (enabled) {
            Settings.Global.putInt(contentResolver, Settings.Global.ADB_ENABLED, 1)
        } else {
            Settings.Global.putInt(contentResolver, Settings.Global.ADB_ENABLED, 0)
        }
    }
}