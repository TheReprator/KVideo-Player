package dev.reprator.video.platform.extra.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName


@Serializable
@XmlSerialName(
    value = "MPD",
    namespace = "urn:mpeg:dash:schema:mpd:2011",
)
data class RawResponseMpdInfo(val period: RawResponsePeriod? = null, val mediaPresentationDuration: String ?= null )

@Serializable
@XmlSerialName("Period", "urn:mpeg:dash:schema:mpd:2011")
data class RawResponsePeriod(
    val id: String?= null,
    val start: String?= null,
    val adaptationSet: List<RawResponseAdaptationSet> = emptyList()
)

@Serializable
@XmlSerialName("AdaptationSet", "urn:mpeg:dash:schema:mpd:2011")
data class RawResponseAdaptationSet(
    val mimeType: String ?= null,
    val group: Int ?= null,
    val lang: String? = null,
    val minBandwidth: Long? = null,
    val maxBandwidth: Long? = null,
    val segmentAlignment: Boolean? = null,
    val startWithSAP: Int? = null, // Specific to video AdaptationSet
    val par: String? = null,       // Specific to video AdaptationSet
    val minWidth: Long? = null,     // Specific to video AdaptationSet
    val maxWidth: Long? = null,     // Specific to video AdaptationSet
    val minHeight: Long? = null,    // Specific to video AdaptationSet
    val maxHeight: Long? = null,    // Specific to video AdaptationSet
    val representation: List<RawResponseRepresentation> = emptyList()
)

@Serializable
@XmlSerialName("Representation",  "urn:mpeg:dash:schema:mpd:2011")
data class RawResponseRepresentation(
    val id: String?= null,
    val bandwidth: Long ?= null,
    val width: Long? = null,
    val height: Long? = null,
    val frameRate: String? = null,
    val codecs: String? = null,
    val audioSamplingRate: Long? = null,
    val baseURL: String? = null,
    val segmentTemplate: RawResponseSegmentTemplate? = null,
    val audioChannelConfiguration: RawResponseAudioChannelConfiguration? = null,
)

@Serializable
@XmlSerialName("SegmentTemplate", "urn:mpeg:dash:schema:mpd:2011")
data class RawResponseSegmentTemplate(
    val timescale: Long? = null,
    val duration: Long? = null,
    val media: String? = null,
    val initialization: String? = null,
    val startNumber: Int? = null
)

@Serializable
@XmlSerialName("AudioChannelConfiguration", "urn:mpeg:dash:schema:mpd:2011", "")
data class RawResponseAudioChannelConfiguration(
    val schemeIdUri: String ?= null,
    val value: String ?= null
)