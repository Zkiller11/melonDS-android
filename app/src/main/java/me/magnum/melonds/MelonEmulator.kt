package me.magnum.melonds

import android.content.res.AssetManager
import me.magnum.melonds.domain.model.EmulatorConfiguration
import me.magnum.melonds.domain.model.Input
import me.magnum.melonds.domain.model.RendererConfiguration
import java.nio.ByteBuffer

object MelonEmulator {
    enum class LoadResult {
        SUCCESS, SUCCESS_GBA_FAILED, NDS_FAILED
    }

    @JvmStatic
	external fun setupEmulator(emulatorConfiguration: EmulatorConfiguration, assetManager: AssetManager?)

    @JvmStatic
	fun loadRom(romPath: String, sramPath: String, loadDirect: Boolean, loadGbaRom: Boolean, gbaRomPath: String?, gbaSramPath: String?): LoadResult {
        val loadResult = loadRomInternal(romPath, sramPath, loadDirect, loadGbaRom, gbaRomPath, gbaSramPath)
        return when (loadResult) {
            0 -> LoadResult.SUCCESS
            1 -> LoadResult.SUCCESS_GBA_FAILED
            2 -> LoadResult.NDS_FAILED
            else -> throw RuntimeException("Unknown load result")
        }
    }

    private external fun loadRomInternal(romPath: String, sramPath: String, loadDirect: Boolean, loadGbaRom: Boolean, gbaRomPath: String?, gbaSramPath: String?): Int

    @JvmStatic
	external fun startEmulation()

    @JvmStatic
	external fun copyFrameBuffer(frameBufferDst: ByteBuffer)

    @JvmStatic
	external fun getFPS(): Int

    @JvmStatic
	external fun pauseEmulation()

    @JvmStatic
	external fun resumeEmulation()

    @JvmStatic
	external fun stopEmulation()

    @JvmStatic
    external fun saveState(path: String): Boolean

    @JvmStatic
    external fun loadState(path: String): Boolean

    @JvmStatic
	external fun onScreenTouch(x: Int, y: Int)

    @JvmStatic
	external fun onScreenRelease()

    @JvmStatic
	fun onInputDown(input: Input) {
        onKeyPress(input.keyCode)
    }

    @JvmStatic
	fun onInputUp(input: Input) {
        onKeyRelease(input.keyCode)
    }

    private external fun onKeyPress(key: Int)

    private external fun onKeyRelease(key: Int)

    @JvmStatic
    external fun setFastForwardEnabled(enabled: Boolean)

    @JvmStatic
    external fun updateEmulatorConfiguration(emulatorConfiguration: EmulatorConfiguration)
}