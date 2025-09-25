package dev.reprator.kmp.video.dashHandler.models


object Mapper {

    fun mapTo(input: RawResponseMpdInfo): MpdInfo {
        val adaptationSet = input.period?.adaptationSet ?: throw Exception("Invalid Response")

        val adaptionSet = mutableListOf<AdaptationSet>()

        adaptationSet.forEach {
            val adaptationSet: AdaptationSet? = when {
                it.mimeType?.contains(MIME_AUDIO) == true -> {
                     val audioRepresentation = it.representation.map { representation ->
                         val rawSegmentTemplate = representation.segmentTemplate
                         val segmentTemplate = SegmentTemplate(rawSegmentTemplate?.timescale ?: 1L,
                             rawSegmentTemplate?.duration ?: 1L, rawSegmentTemplate?.media.orEmpty(),
                             rawSegmentTemplate?.initialization.orEmpty(),
                             rawSegmentTemplate?.startNumber ?: 1)

                         val rawAudio = representation.audioChannelConfiguration
                         val audioChannelConfiguration = AudioChannelConfiguration(rawAudio?.schemeIdUri ?: "", rawAudio?.value?.toInt() ?: 1)

                         val audioRepresentation = AudioRepresentation(representation.id ?: "",
                             representation.bandwidth ?: 1L, representation.codecs ?: "",
                             representation.audioSamplingRate ?: 1L, segmentTemplate,
                             audioChannelConfiguration)
                         audioRepresentation
                     }

                     val adaptationSetAudio = AdaptationSetAudio(
                         it.mimeType,
                         it.lang ?: "", it.group ?: 1, it.minBandwidth ?: 1L,
                         it.maxBandwidth ?: 1L, it.segmentAlignment ?: true,
                         audioRepresentation)

                     adaptationSetAudio
                }
                it.mimeType?.contains(MIME_VIDEO) == true -> {
                    val videoRepresentation = it.representation.map { representation ->
                        val rawSegmentTemplate = representation.segmentTemplate
                        val segmentTemplate = SegmentTemplate(rawSegmentTemplate?.timescale ?: 1L,
                            rawSegmentTemplate?.duration ?: 1L, rawSegmentTemplate?.media.orEmpty(),
                            rawSegmentTemplate?.initialization.orEmpty(),
                            rawSegmentTemplate?.startNumber ?: 1)

                        val videoRepresentation = VideoRepresentation(representation.id ?: "",
                            representation.bandwidth ?: 1L, representation.codecs ?: "",
                            representation.frameRate ?: "", segmentTemplate,
                            representation.width ?: 1L, representation.height ?: 1L)
                        videoRepresentation
                    }

                    val adaptationSetVideo = AdaptationSetVideo( it.group ?: 1,
                        it.mimeType, it.lang ?: "", it.par ?: "",
                        it.segmentAlignment ?: true, it.minBandwidth ?: 1L,
                        it.maxBandwidth ?: 1L,  it.minWidth ?: 1L,
                        it.maxBandwidth ?: 1L, it.minHeight ?: 1L,
                        it.maxHeight ?: 1L, it.startWithSAP ?: 1,
                        videoRepresentation)

                    adaptationSetVideo
                }
                else -> {
                    null
                }
            }

            if (adaptationSet != null)
                adaptionSet.add(adaptationSet)
        }

        // Case 1: Try ISO 8601 format like PT1H2M3S
        val full = Regex("""PT(?:(\d+)H)?(?:(\d+)M)?(?:(\d+(?:\.\d+)?)S)?""")
        val totalTime = full.find(input.mediaPresentationDuration ?: "").let { m ->
            val h = m?.groups[1]?.value?.toFloatOrNull() ?: 0f
            val mm = m?.groups[2]?.value?.toFloatOrNull() ?: 0f
            val s = m?.groups[3]?.value?.toFloatOrNull() ?: 0f
            h * 3600f + mm * 60f + s
        }


        return MpdInfo(totalTime,adaptionSet)
    }
}

/*
* https://github.com/huzhlei/DASH-to-HLS-Playback/blob/master/conversion.js
* https://github.com/h-anders-unext/MPEGDASHAVPlayerDemo
* */