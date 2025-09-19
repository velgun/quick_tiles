package com.velgun.quicktiles

import android.graphics.drawable.Icon
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.provider.Settings

class DeveloperOptionsTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile
        val isEnabled = isDeveloperOptionsEnabled()
        tile.state = if (isEnabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.label = getString(R.string.developer_options)
        tile.icon = Icon.createWithResource(applicationContext, R.drawable.baseline_developer_mode_24)
        tile.updateTile()
    }

    override fun onClick() {
        val isEnabled = isDeveloperOptionsEnabled()
        if (isEnabled) {
            setDeveloperOptions(false)
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            setDeveloperOptions(true)
            qsTile.state = Tile.STATE_ACTIVE
        }

        qsTile.updateTile()
    }

    private fun isDeveloperOptionsEnabled(): Boolean {
        return try {
            Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1
        } catch (_: Settings.SettingNotFoundException) {
            false
        }
    }

    private fun setDeveloperOptions(enabled: Boolean) {
        if (enabled) {
            Settings.Global.putInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 1)
        } else {
            Settings.Global.putInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0)
        }
    }
}