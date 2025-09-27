#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class AVPlayer, VFWKMPAdaptationSetAudio, VFWKMPAdaptationSetVideo, VFWKMPAudioChannelConfiguration, VFWKMPAudioRepresentation, VFWKMPKotlinArray<T>, VFWKMPKotlinEnum<E>, VFWKMPKotlinEnumCompanion, VFWKMPKotlinException, VFWKMPKotlinIllegalStateException, VFWKMPKotlinNothing, VFWKMPKotlinRuntimeException, VFWKMPKotlinThrowable, VFWKMPKotlinx_serialization_coreSerialKind, VFWKMPKotlinx_serialization_coreSerializersModule, VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode, VFWKMPKotlinx_serialization_jsonJson, VFWKMPKotlinx_serialization_jsonJsonConfiguration, VFWKMPKotlinx_serialization_jsonJsonDefault, VFWKMPKotlinx_serialization_jsonJsonElement, VFWKMPKotlinx_serialization_jsonJsonElementCompanion, VFWKMPMapper, VFWKMPMpdInfo, VFWKMPRawResponseAdaptationSet, VFWKMPRawResponseAdaptationSetCompanion, VFWKMPRawResponseAudioChannelConfiguration, VFWKMPRawResponseAudioChannelConfigurationCompanion, VFWKMPRawResponseMpdInfo, VFWKMPRawResponseMpdInfoCompanion, VFWKMPRawResponsePeriod, VFWKMPRawResponsePeriodCompanion, VFWKMPRawResponseRepresentation, VFWKMPRawResponseRepresentationCompanion, VFWKMPRawResponseSegmentTemplate, VFWKMPRawResponseSegmentTemplateCompanion, VFWKMPSegmentTemplate, VFWKMPVideoConnectivityHandlerCompanion, VFWKMPVideoConnectivityMessageCompanion, VFWKMPVideoConnectivityMessageErrorResponse, VFWKMPVideoConnectivityMessageErrorResponseCompanion, VFWKMPVideoConnectivityMessagePlaybackResponse, VFWKMPVideoConnectivityMessagePlaybackResponseCompanion, VFWKMPVideoConnectivityMessageRequestPlayback, VFWKMPVideoConnectivityMessageRequestPlaybackCompanion, VFWKMPVideoInitOptionModal, VFWKMPVideoRepresentation, VFWKMPVideoSource;

@protocol VFWKMPAdaptationSet, VFWKMPKotlinAnnotation, VFWKMPKotlinComparable, VFWKMPKotlinIterator, VFWKMPKotlinKAnnotatedElement, VFWKMPKotlinKClass, VFWKMPKotlinKClassifier, VFWKMPKotlinKDeclarationContainer, VFWKMPKotlinx_serialization_coreCompositeDecoder, VFWKMPKotlinx_serialization_coreCompositeEncoder, VFWKMPKotlinx_serialization_coreDecoder, VFWKMPKotlinx_serialization_coreDeserializationStrategy, VFWKMPKotlinx_serialization_coreEncoder, VFWKMPKotlinx_serialization_coreKSerializer, VFWKMPKotlinx_serialization_coreSerialDescriptor, VFWKMPKotlinx_serialization_coreSerialFormat, VFWKMPKotlinx_serialization_coreSerializationStrategy, VFWKMPKotlinx_serialization_coreSerializersModuleCollector, VFWKMPKotlinx_serialization_coreStringFormat, VFWKMPKotlinx_serialization_jsonJsonNamingStrategy, VFWKMPPlayerController, VFWKMPRepresentation, VFWKMPVideoConnectivityHandler, VFWKMPVideoConnectivityMessage, VFWKMPVideoPlayer;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface VFWKMPBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface VFWKMPBase (VFWKMPBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface VFWKMPMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface VFWKMPMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorVFWKMPKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface VFWKMPNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface VFWKMPByte : VFWKMPNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface VFWKMPUByte : VFWKMPNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface VFWKMPShort : VFWKMPNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface VFWKMPUShort : VFWKMPNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface VFWKMPInt : VFWKMPNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface VFWKMPUInt : VFWKMPNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface VFWKMPLong : VFWKMPNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface VFWKMPULong : VFWKMPNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface VFWKMPFloat : VFWKMPNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface VFWKMPDouble : VFWKMPNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface VFWKMPBoolean : VFWKMPNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("AdaptationSet")))
@protocol VFWKMPAdaptationSet
@required
@property (readonly) NSString *defaultType __attribute__((swift_name("defaultType")));
@property (readonly) int32_t group __attribute__((swift_name("group")));
@property (readonly) NSString *lang __attribute__((swift_name("lang")));
@property (readonly) NSString *mimeType __attribute__((swift_name("mimeType")));
@property (readonly) NSArray<id<VFWKMPRepresentation>> *representation __attribute__((swift_name("representation")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AdaptationSetAudio")))
@interface VFWKMPAdaptationSetAudio : VFWKMPBase <VFWKMPAdaptationSet>
- (instancetype)initWithMimeType:(NSString *)mimeType lang:(NSString *)lang group:(int32_t)group minBandwidth:(int64_t)minBandwidth maxBandwidth:(int64_t)maxBandwidth segmentAlignment:(BOOL)segmentAlignment representation:(NSArray<VFWKMPAudioRepresentation *> *)representation defaultType:(NSString *)defaultType __attribute__((swift_name("init(mimeType:lang:group:minBandwidth:maxBandwidth:segmentAlignment:representation:defaultType:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPAdaptationSetAudio *)doCopyMimeType:(NSString *)mimeType lang:(NSString *)lang group:(int32_t)group minBandwidth:(int64_t)minBandwidth maxBandwidth:(int64_t)maxBandwidth segmentAlignment:(BOOL)segmentAlignment representation:(NSArray<VFWKMPAudioRepresentation *> *)representation defaultType:(NSString *)defaultType __attribute__((swift_name("doCopy(mimeType:lang:group:minBandwidth:maxBandwidth:segmentAlignment:representation:defaultType:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *defaultType __attribute__((swift_name("defaultType")));
@property (readonly) int32_t group __attribute__((swift_name("group")));
@property (readonly) NSString *lang __attribute__((swift_name("lang")));
@property (readonly) int64_t maxBandwidth __attribute__((swift_name("maxBandwidth")));
@property (readonly) NSString *mimeType __attribute__((swift_name("mimeType")));
@property (readonly) int64_t minBandwidth __attribute__((swift_name("minBandwidth")));
@property (readonly) NSArray<VFWKMPAudioRepresentation *> *representation __attribute__((swift_name("representation")));
@property (readonly) BOOL segmentAlignment __attribute__((swift_name("segmentAlignment")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AdaptationSetVideo")))
@interface VFWKMPAdaptationSetVideo : VFWKMPBase <VFWKMPAdaptationSet>
- (instancetype)initWithGroup:(int32_t)group mimeType:(NSString *)mimeType lang:(NSString *)lang par:(NSString *)par segmentAlignment:(BOOL)segmentAlignment minBandwidth:(int64_t)minBandwidth maxBandwidth:(int64_t)maxBandwidth minWidth:(int64_t)minWidth maxWidth:(int64_t)maxWidth minHeight:(int64_t)minHeight maxHeight:(int64_t)maxHeight startWithSAP:(int32_t)startWithSAP representation:(NSArray<VFWKMPVideoRepresentation *> *)representation defaultType:(NSString *)defaultType __attribute__((swift_name("init(group:mimeType:lang:par:segmentAlignment:minBandwidth:maxBandwidth:minWidth:maxWidth:minHeight:maxHeight:startWithSAP:representation:defaultType:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPAdaptationSetVideo *)doCopyGroup:(int32_t)group mimeType:(NSString *)mimeType lang:(NSString *)lang par:(NSString *)par segmentAlignment:(BOOL)segmentAlignment minBandwidth:(int64_t)minBandwidth maxBandwidth:(int64_t)maxBandwidth minWidth:(int64_t)minWidth maxWidth:(int64_t)maxWidth minHeight:(int64_t)minHeight maxHeight:(int64_t)maxHeight startWithSAP:(int32_t)startWithSAP representation:(NSArray<VFWKMPVideoRepresentation *> *)representation defaultType:(NSString *)defaultType __attribute__((swift_name("doCopy(group:mimeType:lang:par:segmentAlignment:minBandwidth:maxBandwidth:minWidth:maxWidth:minHeight:maxHeight:startWithSAP:representation:defaultType:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *defaultType __attribute__((swift_name("defaultType")));
@property (readonly) int32_t group __attribute__((swift_name("group")));
@property (readonly) NSString *lang __attribute__((swift_name("lang")));
@property (readonly) int64_t maxBandwidth __attribute__((swift_name("maxBandwidth")));
@property (readonly) int64_t maxHeight __attribute__((swift_name("maxHeight")));
@property (readonly) int64_t maxWidth __attribute__((swift_name("maxWidth")));
@property (readonly) NSString *mimeType __attribute__((swift_name("mimeType")));
@property (readonly) int64_t minBandwidth __attribute__((swift_name("minBandwidth")));
@property (readonly) int64_t minHeight __attribute__((swift_name("minHeight")));
@property (readonly) int64_t minWidth __attribute__((swift_name("minWidth")));
@property (readonly) NSString *par __attribute__((swift_name("par")));
@property (readonly) NSArray<VFWKMPVideoRepresentation *> *representation __attribute__((swift_name("representation")));
@property (readonly) BOOL segmentAlignment __attribute__((swift_name("segmentAlignment")));
@property (readonly) int32_t startWithSAP __attribute__((swift_name("startWithSAP")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AudioChannelConfiguration")))
@interface VFWKMPAudioChannelConfiguration : VFWKMPBase
- (instancetype)initWithSchemeIdUri:(NSString *)schemeIdUri value:(int32_t)value __attribute__((swift_name("init(schemeIdUri:value:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPAudioChannelConfiguration *)doCopySchemeIdUri:(NSString *)schemeIdUri value:(int32_t)value __attribute__((swift_name("doCopy(schemeIdUri:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *schemeIdUri __attribute__((swift_name("schemeIdUri")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("Representation")))
@protocol VFWKMPRepresentation
@required
@property (readonly) int64_t bandwidth __attribute__((swift_name("bandwidth")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AudioRepresentation")))
@interface VFWKMPAudioRepresentation : VFWKMPBase <VFWKMPRepresentation>
- (instancetype)initWithId:(NSString *)id bandwidth:(int64_t)bandwidth codecs:(NSString *)codecs audioSamplingRate:(int64_t)audioSamplingRate segmentTemplate:(VFWKMPSegmentTemplate *)segmentTemplate audioChannelConfiguration:(VFWKMPAudioChannelConfiguration *)audioChannelConfiguration __attribute__((swift_name("init(id:bandwidth:codecs:audioSamplingRate:segmentTemplate:audioChannelConfiguration:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPAudioRepresentation *)doCopyId:(NSString *)id bandwidth:(int64_t)bandwidth codecs:(NSString *)codecs audioSamplingRate:(int64_t)audioSamplingRate segmentTemplate:(VFWKMPSegmentTemplate *)segmentTemplate audioChannelConfiguration:(VFWKMPAudioChannelConfiguration *)audioChannelConfiguration __attribute__((swift_name("doCopy(id:bandwidth:codecs:audioSamplingRate:segmentTemplate:audioChannelConfiguration:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) VFWKMPAudioChannelConfiguration *audioChannelConfiguration __attribute__((swift_name("audioChannelConfiguration")));
@property (readonly) int64_t audioSamplingRate __attribute__((swift_name("audioSamplingRate")));
@property (readonly) int64_t bandwidth __attribute__((swift_name("bandwidth")));
@property (readonly) NSString *codecs __attribute__((swift_name("codecs")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) VFWKMPSegmentTemplate *segmentTemplate __attribute__((swift_name("segmentTemplate")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Mapper")))
@interface VFWKMPMapper : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)mapper __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPMapper *shared __attribute__((swift_name("shared")));
- (VFWKMPMpdInfo *)mapToInput:(VFWKMPRawResponseMpdInfo *)input __attribute__((swift_name("mapTo(input:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MpdInfo")))
@interface VFWKMPMpdInfo : VFWKMPBase
- (instancetype)initWithTotalDuration:(float)totalDuration adaptationSets:(NSArray<id<VFWKMPAdaptationSet>> *)adaptationSets __attribute__((swift_name("init(totalDuration:adaptationSets:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPMpdInfo *)doCopyTotalDuration:(float)totalDuration adaptationSets:(NSArray<id<VFWKMPAdaptationSet>> *)adaptationSets __attribute__((swift_name("doCopy(totalDuration:adaptationSets:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<id<VFWKMPAdaptationSet>> *adaptationSets __attribute__((swift_name("adaptationSets")));
@property (readonly) float totalDuration __attribute__((swift_name("totalDuration")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseAdaptationSet")))
@interface VFWKMPRawResponseAdaptationSet : VFWKMPBase
- (instancetype)initWithMimeType:(NSString * _Nullable)mimeType group:(VFWKMPInt * _Nullable)group lang:(NSString * _Nullable)lang minBandwidth:(VFWKMPLong * _Nullable)minBandwidth maxBandwidth:(VFWKMPLong * _Nullable)maxBandwidth segmentAlignment:(VFWKMPBoolean * _Nullable)segmentAlignment startWithSAP:(VFWKMPInt * _Nullable)startWithSAP par:(NSString * _Nullable)par minWidth:(VFWKMPLong * _Nullable)minWidth maxWidth:(VFWKMPLong * _Nullable)maxWidth minHeight:(VFWKMPLong * _Nullable)minHeight maxHeight:(VFWKMPLong * _Nullable)maxHeight representation:(NSArray<VFWKMPRawResponseRepresentation *> *)representation __attribute__((swift_name("init(mimeType:group:lang:minBandwidth:maxBandwidth:segmentAlignment:startWithSAP:par:minWidth:maxWidth:minHeight:maxHeight:representation:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponseAdaptationSetCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponseAdaptationSet *)doCopyMimeType:(NSString * _Nullable)mimeType group:(VFWKMPInt * _Nullable)group lang:(NSString * _Nullable)lang minBandwidth:(VFWKMPLong * _Nullable)minBandwidth maxBandwidth:(VFWKMPLong * _Nullable)maxBandwidth segmentAlignment:(VFWKMPBoolean * _Nullable)segmentAlignment startWithSAP:(VFWKMPInt * _Nullable)startWithSAP par:(NSString * _Nullable)par minWidth:(VFWKMPLong * _Nullable)minWidth maxWidth:(VFWKMPLong * _Nullable)maxWidth minHeight:(VFWKMPLong * _Nullable)minHeight maxHeight:(VFWKMPLong * _Nullable)maxHeight representation:(NSArray<VFWKMPRawResponseRepresentation *> *)representation __attribute__((swift_name("doCopy(mimeType:group:lang:minBandwidth:maxBandwidth:segmentAlignment:startWithSAP:par:minWidth:maxWidth:minHeight:maxHeight:representation:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) VFWKMPInt * _Nullable group __attribute__((swift_name("group")));
@property (readonly) NSString * _Nullable lang __attribute__((swift_name("lang")));
@property (readonly) VFWKMPLong * _Nullable maxBandwidth __attribute__((swift_name("maxBandwidth")));
@property (readonly) VFWKMPLong * _Nullable maxHeight __attribute__((swift_name("maxHeight")));
@property (readonly) VFWKMPLong * _Nullable maxWidth __attribute__((swift_name("maxWidth")));
@property (readonly) NSString * _Nullable mimeType __attribute__((swift_name("mimeType")));
@property (readonly) VFWKMPLong * _Nullable minBandwidth __attribute__((swift_name("minBandwidth")));
@property (readonly) VFWKMPLong * _Nullable minHeight __attribute__((swift_name("minHeight")));
@property (readonly) VFWKMPLong * _Nullable minWidth __attribute__((swift_name("minWidth")));
@property (readonly) NSString * _Nullable par __attribute__((swift_name("par")));
@property (readonly) NSArray<VFWKMPRawResponseRepresentation *> *representation __attribute__((swift_name("representation")));
@property (readonly) VFWKMPBoolean * _Nullable segmentAlignment __attribute__((swift_name("segmentAlignment")));
@property (readonly) VFWKMPInt * _Nullable startWithSAP __attribute__((swift_name("startWithSAP")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseAdaptationSet.Companion")))
@interface VFWKMPRawResponseAdaptationSetCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponseAdaptationSetCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseAudioChannelConfiguration")))
@interface VFWKMPRawResponseAudioChannelConfiguration : VFWKMPBase
- (instancetype)initWithSchemeIdUri:(NSString * _Nullable)schemeIdUri value:(NSString * _Nullable)value __attribute__((swift_name("init(schemeIdUri:value:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponseAudioChannelConfigurationCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponseAudioChannelConfiguration *)doCopySchemeIdUri:(NSString * _Nullable)schemeIdUri value:(NSString * _Nullable)value __attribute__((swift_name("doCopy(schemeIdUri:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable schemeIdUri __attribute__((swift_name("schemeIdUri")));
@property (readonly) NSString * _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseAudioChannelConfiguration.Companion")))
@interface VFWKMPRawResponseAudioChannelConfigurationCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponseAudioChannelConfigurationCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseMpdInfo")))
@interface VFWKMPRawResponseMpdInfo : VFWKMPBase
- (instancetype)initWithPeriod:(VFWKMPRawResponsePeriod * _Nullable)period mediaPresentationDuration:(NSString * _Nullable)mediaPresentationDuration __attribute__((swift_name("init(period:mediaPresentationDuration:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponseMpdInfoCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponseMpdInfo *)doCopyPeriod:(VFWKMPRawResponsePeriod * _Nullable)period mediaPresentationDuration:(NSString * _Nullable)mediaPresentationDuration __attribute__((swift_name("doCopy(period:mediaPresentationDuration:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable mediaPresentationDuration __attribute__((swift_name("mediaPresentationDuration")));
@property (readonly) VFWKMPRawResponsePeriod * _Nullable period __attribute__((swift_name("period")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseMpdInfo.Companion")))
@interface VFWKMPRawResponseMpdInfoCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponseMpdInfoCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponsePeriod")))
@interface VFWKMPRawResponsePeriod : VFWKMPBase
- (instancetype)initWithId:(NSString * _Nullable)id start:(NSString * _Nullable)start adaptationSet:(NSArray<VFWKMPRawResponseAdaptationSet *> *)adaptationSet __attribute__((swift_name("init(id:start:adaptationSet:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponsePeriodCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponsePeriod *)doCopyId:(NSString * _Nullable)id start:(NSString * _Nullable)start adaptationSet:(NSArray<VFWKMPRawResponseAdaptationSet *> *)adaptationSet __attribute__((swift_name("doCopy(id:start:adaptationSet:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<VFWKMPRawResponseAdaptationSet *> *adaptationSet __attribute__((swift_name("adaptationSet")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable start __attribute__((swift_name("start")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponsePeriod.Companion")))
@interface VFWKMPRawResponsePeriodCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponsePeriodCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseRepresentation")))
@interface VFWKMPRawResponseRepresentation : VFWKMPBase
- (instancetype)initWithId:(NSString * _Nullable)id bandwidth:(VFWKMPLong * _Nullable)bandwidth width:(VFWKMPLong * _Nullable)width height:(VFWKMPLong * _Nullable)height frameRate:(NSString * _Nullable)frameRate codecs:(NSString * _Nullable)codecs audioSamplingRate:(VFWKMPLong * _Nullable)audioSamplingRate baseURL:(NSString * _Nullable)baseURL segmentTemplate:(VFWKMPRawResponseSegmentTemplate * _Nullable)segmentTemplate audioChannelConfiguration:(VFWKMPRawResponseAudioChannelConfiguration * _Nullable)audioChannelConfiguration __attribute__((swift_name("init(id:bandwidth:width:height:frameRate:codecs:audioSamplingRate:baseURL:segmentTemplate:audioChannelConfiguration:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponseRepresentationCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponseRepresentation *)doCopyId:(NSString * _Nullable)id bandwidth:(VFWKMPLong * _Nullable)bandwidth width:(VFWKMPLong * _Nullable)width height:(VFWKMPLong * _Nullable)height frameRate:(NSString * _Nullable)frameRate codecs:(NSString * _Nullable)codecs audioSamplingRate:(VFWKMPLong * _Nullable)audioSamplingRate baseURL:(NSString * _Nullable)baseURL segmentTemplate:(VFWKMPRawResponseSegmentTemplate * _Nullable)segmentTemplate audioChannelConfiguration:(VFWKMPRawResponseAudioChannelConfiguration * _Nullable)audioChannelConfiguration __attribute__((swift_name("doCopy(id:bandwidth:width:height:frameRate:codecs:audioSamplingRate:baseURL:segmentTemplate:audioChannelConfiguration:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) VFWKMPRawResponseAudioChannelConfiguration * _Nullable audioChannelConfiguration __attribute__((swift_name("audioChannelConfiguration")));
@property (readonly) VFWKMPLong * _Nullable audioSamplingRate __attribute__((swift_name("audioSamplingRate")));
@property (readonly) VFWKMPLong * _Nullable bandwidth __attribute__((swift_name("bandwidth")));
@property (readonly) NSString * _Nullable baseURL __attribute__((swift_name("baseURL")));
@property (readonly) NSString * _Nullable codecs __attribute__((swift_name("codecs")));
@property (readonly) NSString * _Nullable frameRate __attribute__((swift_name("frameRate")));
@property (readonly) VFWKMPLong * _Nullable height __attribute__((swift_name("height")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) VFWKMPRawResponseSegmentTemplate * _Nullable segmentTemplate __attribute__((swift_name("segmentTemplate")));
@property (readonly) VFWKMPLong * _Nullable width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseRepresentation.Companion")))
@interface VFWKMPRawResponseRepresentationCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponseRepresentationCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseSegmentTemplate")))
@interface VFWKMPRawResponseSegmentTemplate : VFWKMPBase
- (instancetype)initWithTimescale:(VFWKMPLong * _Nullable)timescale duration:(VFWKMPLong * _Nullable)duration media:(NSString * _Nullable)media initialization:(NSString * _Nullable)initialization startNumber:(VFWKMPInt * _Nullable)startNumber __attribute__((swift_name("init(timescale:duration:media:initialization:startNumber:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPRawResponseSegmentTemplateCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPRawResponseSegmentTemplate *)doCopyTimescale:(VFWKMPLong * _Nullable)timescale duration:(VFWKMPLong * _Nullable)duration media:(NSString * _Nullable)media initialization:(NSString * _Nullable)initialization startNumber:(VFWKMPInt * _Nullable)startNumber __attribute__((swift_name("doCopy(timescale:duration:media:initialization:startNumber:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) VFWKMPLong * _Nullable duration __attribute__((swift_name("duration")));
@property (readonly) NSString * _Nullable initialization __attribute__((swift_name("initialization")));
@property (readonly) NSString * _Nullable media __attribute__((swift_name("media")));
@property (readonly) VFWKMPInt * _Nullable startNumber __attribute__((swift_name("startNumber")));
@property (readonly) VFWKMPLong * _Nullable timescale __attribute__((swift_name("timescale")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RawResponseSegmentTemplate.Companion")))
@interface VFWKMPRawResponseSegmentTemplateCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPRawResponseSegmentTemplateCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SegmentTemplate")))
@interface VFWKMPSegmentTemplate : VFWKMPBase
- (instancetype)initWithTimescale:(int64_t)timescale duration:(int64_t)duration media:(NSString *)media initialization:(NSString *)initialization startNumber:(int32_t)startNumber __attribute__((swift_name("init(timescale:duration:media:initialization:startNumber:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPSegmentTemplate *)doCopyTimescale:(int64_t)timescale duration:(int64_t)duration media:(NSString *)media initialization:(NSString *)initialization startNumber:(int32_t)startNumber __attribute__((swift_name("doCopy(timescale:duration:media:initialization:startNumber:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t duration __attribute__((swift_name("duration")));
@property (readonly) NSString *initialization __attribute__((swift_name("initialization")));
@property (readonly) NSString *media __attribute__((swift_name("media")));
@property (readonly) int32_t startNumber __attribute__((swift_name("startNumber")));
@property (readonly) int64_t timescale __attribute__((swift_name("timescale")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoRepresentation")))
@interface VFWKMPVideoRepresentation : VFWKMPBase <VFWKMPRepresentation>
- (instancetype)initWithId:(NSString *)id bandwidth:(int64_t)bandwidth codecs:(NSString *)codecs frameRate:(NSString *)frameRate segmentTemplate:(VFWKMPSegmentTemplate *)segmentTemplate width:(int64_t)width height:(int64_t)height __attribute__((swift_name("init(id:bandwidth:codecs:frameRate:segmentTemplate:width:height:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPVideoRepresentation *)doCopyId:(NSString *)id bandwidth:(int64_t)bandwidth codecs:(NSString *)codecs frameRate:(NSString *)frameRate segmentTemplate:(VFWKMPSegmentTemplate *)segmentTemplate width:(int64_t)width height:(int64_t)height __attribute__((swift_name("doCopy(id:bandwidth:codecs:frameRate:segmentTemplate:width:height:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t bandwidth __attribute__((swift_name("bandwidth")));
@property (readonly) NSString *codecs __attribute__((swift_name("codecs")));
@property (readonly) NSString *frameRate __attribute__((swift_name("frameRate")));
@property (readonly) int64_t height __attribute__((swift_name("height")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) VFWKMPSegmentTemplate *segmentTemplate __attribute__((swift_name("segmentTemplate")));
@property (readonly) int64_t width __attribute__((swift_name("width")));
@end

__attribute__((swift_name("VideoConnectivityHandler")))
@protocol VFWKMPVideoConnectivityHandler
@required
- (void)sendMessageMessage:(id<VFWKMPVideoConnectivityMessage>)message onResponse:(void (^)(id<VFWKMPVideoConnectivityMessage>))onResponse __attribute__((swift_name("sendMessage(message:onResponse:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityHandlerCompanion")))
@interface VFWKMPVideoConnectivityHandlerCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPVideoConnectivityHandlerCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) VFWKMPKotlinx_serialization_jsonJson *json __attribute__((swift_name("json")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((swift_name("VideoConnectivityMessage")))
@protocol VFWKMPVideoConnectivityMessage
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessageCompanion")))
@interface VFWKMPVideoConnectivityMessageCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPVideoConnectivityMessageCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(VFWKMPKotlinArray<id<VFWKMPKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessageErrorResponse")))
@interface VFWKMPVideoConnectivityMessageErrorResponse : VFWKMPBase <VFWKMPVideoConnectivityMessage>
- (instancetype)initWithError:(NSString *)error requestId:(NSString *)requestId __attribute__((swift_name("init(error:requestId:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPVideoConnectivityMessageErrorResponseCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPVideoConnectivityMessageErrorResponse *)doCopyError:(NSString *)error requestId:(NSString *)requestId __attribute__((swift_name("doCopy(error:requestId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *error __attribute__((swift_name("error")));
@property (readonly) NSString *requestId __attribute__((swift_name("requestId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessageErrorResponse.Companion")))
@interface VFWKMPVideoConnectivityMessageErrorResponseCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPVideoConnectivityMessageErrorResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessagePlaybackResponse")))
@interface VFWKMPVideoConnectivityMessagePlaybackResponse : VFWKMPBase <VFWKMPVideoConnectivityMessage>
- (instancetype)initWithHlsUrl:(NSString *)hlsUrl sessionId:(NSString *)sessionId requestId:(NSString *)requestId __attribute__((swift_name("init(hlsUrl:sessionId:requestId:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPVideoConnectivityMessagePlaybackResponseCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPVideoConnectivityMessagePlaybackResponse *)doCopyHlsUrl:(NSString *)hlsUrl sessionId:(NSString *)sessionId requestId:(NSString *)requestId __attribute__((swift_name("doCopy(hlsUrl:sessionId:requestId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *hlsUrl __attribute__((swift_name("hlsUrl")));
@property (readonly) NSString *requestId __attribute__((swift_name("requestId")));
@property (readonly) NSString *sessionId __attribute__((swift_name("sessionId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessagePlaybackResponse.Companion")))
@interface VFWKMPVideoConnectivityMessagePlaybackResponseCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPVideoConnectivityMessagePlaybackResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessageRequestPlayback")))
@interface VFWKMPVideoConnectivityMessageRequestPlayback : VFWKMPBase <VFWKMPVideoConnectivityMessage>
- (instancetype)initWithMpdUrl:(NSString *)mpdUrl requestId:(NSString *)requestId __attribute__((swift_name("init(mpdUrl:requestId:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPVideoConnectivityMessageRequestPlaybackCompanion *companion __attribute__((swift_name("companion")));
- (VFWKMPVideoConnectivityMessageRequestPlayback *)doCopyMpdUrl:(NSString *)mpdUrl requestId:(NSString *)requestId __attribute__((swift_name("doCopy(mpdUrl:requestId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *mpdUrl __attribute__((swift_name("mpdUrl")));
@property (readonly) NSString *requestId __attribute__((swift_name("requestId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoConnectivityMessageRequestPlayback.Companion")))
@interface VFWKMPVideoConnectivityMessageRequestPlaybackCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPVideoConnectivityMessageRequestPlaybackCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WatchVideoConnectivityHandler")))
@interface VFWKMPWatchVideoConnectivityHandler : VFWKMPBase <VFWKMPVideoConnectivityHandler>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)sendMessageMessage:(id<VFWKMPVideoConnectivityMessage>)message onResponse:(void (^)(id<VFWKMPVideoConnectivityMessage>))onResponse __attribute__((swift_name("sendMessage(message:onResponse:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoInitOptionModal")))
@interface VFWKMPVideoInitOptionModal : VFWKMPBase
- (instancetype)initWithControls:(BOOL)controls autoplay:(BOOL)autoplay poster:(NSString * _Nullable)poster preload:(NSString *)preload muted:(BOOL)muted id:(NSString * _Nullable)id sources:(NSArray<VFWKMPVideoSource *> *)sources __attribute__((swift_name("init(controls:autoplay:poster:preload:muted:id:sources:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPVideoInitOptionModal *)doCopyControls:(BOOL)controls autoplay:(BOOL)autoplay poster:(NSString * _Nullable)poster preload:(NSString *)preload muted:(BOOL)muted id:(NSString * _Nullable)id sources:(NSArray<VFWKMPVideoSource *> *)sources __attribute__((swift_name("doCopy(controls:autoplay:poster:preload:muted:id:sources:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL autoplay __attribute__((swift_name("autoplay")));
@property (readonly) BOOL controls __attribute__((swift_name("controls")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) BOOL muted __attribute__((swift_name("muted")));
@property (readonly) NSString * _Nullable poster __attribute__((swift_name("poster")));
@property (readonly) NSString *preload __attribute__((swift_name("preload")));
@property (readonly) NSArray<VFWKMPVideoSource *> *sources __attribute__((swift_name("sources")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoSource")))
@interface VFWKMPVideoSource : VFWKMPBase
- (instancetype)initWithSrc:(NSString *)src poster:(NSString *)poster __attribute__((swift_name("init(src:poster:)"))) __attribute__((objc_designated_initializer));
- (VFWKMPVideoSource *)doCopySrc:(NSString *)src poster:(NSString *)poster __attribute__((swift_name("doCopy(src:poster:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *poster __attribute__((swift_name("poster")));
@property (readonly) NSString *src __attribute__((swift_name("src")));
@end

__attribute__((swift_name("PlayerController")))
@protocol VFWKMPPlayerController
@required
- (void)doInitPlayerInitOptions:(VFWKMPVideoInitOptionModal *)initOptions __attribute__((swift_name("doInitPlayer(initOptions:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setupPlayerWithCompletionHandler:(void (^)(VFWKMPBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setupPlayer(completionHandler:)")));
@property (readonly) id<VFWKMPVideoPlayer> player __attribute__((swift_name("player")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlaybackStateControllerImplWatch")))
@interface VFWKMPPlaybackStateControllerImplWatch : VFWKMPBase <VFWKMPPlayerController>
- (instancetype)initWithConnectivityHandler:(id<VFWKMPVideoConnectivityHandler>)connectivityHandler __attribute__((swift_name("init(connectivityHandler:)"))) __attribute__((objc_designated_initializer));
- (void)doInitPlayerInitOptions:(VFWKMPVideoInitOptionModal *)initOptions __attribute__((swift_name("doInitPlayer(initOptions:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setupPlayerWithCompletionHandler:(void (^)(VFWKMPBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setupPlayer(completionHandler:)")));
@property id<VFWKMPVideoPlayer> player __attribute__((swift_name("player")));
@end

__attribute__((swift_name("VideoPlayer")))
@protocol VFWKMPVideoPlayer
@required
- (void)changeMediaVideoSource:(VFWKMPVideoSource *)videoSource __attribute__((swift_name("changeMedia(videoSource:)")));
- (void)dispose __attribute__((swift_name("dispose()")));
- (BOOL)isDisposed __attribute__((swift_name("isDisposed()")));
- (void)pause __attribute__((swift_name("pause()")));
- (void)play __attribute__((swift_name("play()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VideoPlayerImplWatch")))
@interface VFWKMPVideoPlayerImplWatch : VFWKMPBase <VFWKMPVideoPlayer>
- (instancetype)initWithPlayer:(AVPlayer *)player connectivityHandler:(id<VFWKMPVideoConnectivityHandler>)connectivityHandler __attribute__((swift_name("init(player:connectivityHandler:)"))) __attribute__((objc_designated_initializer));
- (void)changeMediaVideoSource:(VFWKMPVideoSource *)videoSource __attribute__((swift_name("changeMedia(videoSource:)")));
- (void)dispose __attribute__((swift_name("dispose()")));
- (BOOL)isDisposed __attribute__((swift_name("isDisposed()")));
- (void)pause __attribute__((swift_name("pause()")));
- (void)play __attribute__((swift_name("play()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ModelsKt")))
@interface VFWKMPModelsKt : VFWKMPBase
@property (class, readonly) NSString *MIME_AUDIO __attribute__((swift_name("MIME_AUDIO")));
@property (class, readonly) NSString *MIME_VIDEO __attribute__((swift_name("MIME_VIDEO")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol VFWKMPKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<VFWKMPKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<VFWKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol VFWKMPKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<VFWKMPKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<VFWKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol VFWKMPKotlinx_serialization_coreKSerializer <VFWKMPKotlinx_serialization_coreSerializationStrategy, VFWKMPKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialFormat")))
@protocol VFWKMPKotlinx_serialization_coreSerialFormat
@required
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreStringFormat")))
@protocol VFWKMPKotlinx_serialization_coreStringFormat <VFWKMPKotlinx_serialization_coreSerialFormat>
@required
- (id _Nullable)decodeFromStringDeserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NSString *)encodeToStringSerializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_jsonJson")))
@interface VFWKMPKotlinx_serialization_jsonJson : VFWKMPBase <VFWKMPKotlinx_serialization_coreStringFormat>
@property (class, readonly, getter=companion) VFWKMPKotlinx_serialization_jsonJsonDefault *companion __attribute__((swift_name("companion")));
- (id _Nullable)decodeFromJsonElementDeserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer element:(VFWKMPKotlinx_serialization_jsonJsonElement *)element __attribute__((swift_name("decodeFromJsonElement(deserializer:element:)")));
- (id _Nullable)decodeFromStringString:(NSString *)string __attribute__((swift_name("decodeFromString(string:)")));
- (id _Nullable)decodeFromStringDeserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (VFWKMPKotlinx_serialization_jsonJsonElement *)encodeToJsonElementSerializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToJsonElement(serializer:value:)")));
- (NSString *)encodeToStringValue:(id _Nullable)value __attribute__((swift_name("encodeToString(value:)")));
- (NSString *)encodeToStringSerializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
- (VFWKMPKotlinx_serialization_jsonJsonElement *)parseToJsonElementString:(NSString *)string __attribute__((swift_name("parseToJsonElement(string:)")));
@property (readonly) VFWKMPKotlinx_serialization_jsonJsonConfiguration *configuration __attribute__((swift_name("configuration")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface VFWKMPKotlinArray<T> : VFWKMPBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(VFWKMPInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<VFWKMPKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface VFWKMPKotlinThrowable : VFWKMPBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (VFWKMPKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) VFWKMPKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface VFWKMPKotlinException : VFWKMPKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface VFWKMPKotlinRuntimeException : VFWKMPKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface VFWKMPKotlinIllegalStateException : VFWKMPKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface VFWKMPKotlinCancellationException : VFWKMPKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(VFWKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol VFWKMPKotlinx_serialization_coreEncoder
@required
- (id<VFWKMPKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<VFWKMPKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<VFWKMPKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol VFWKMPKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<VFWKMPKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<VFWKMPKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol VFWKMPKotlinx_serialization_coreDecoder
@required
- (id<VFWKMPKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<VFWKMPKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (VFWKMPKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface VFWKMPKotlinx_serialization_coreSerializersModule : VFWKMPBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<VFWKMPKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<VFWKMPKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<VFWKMPKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<VFWKMPKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<VFWKMPKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<VFWKMPKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<VFWKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<VFWKMPKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJson.Default")))
@interface VFWKMPKotlinx_serialization_jsonJsonDefault : VFWKMPKotlinx_serialization_jsonJson
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)default_ __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPKotlinx_serialization_jsonJsonDefault *shared __attribute__((swift_name("shared")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/serialization/json/JsonElementSerializer))
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface VFWKMPKotlinx_serialization_jsonJsonElement : VFWKMPBase
@property (class, readonly, getter=companion) VFWKMPKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonConfiguration")))
@interface VFWKMPKotlinx_serialization_jsonJsonConfiguration : VFWKMPBase
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL allowComments __attribute__((swift_name("allowComments")));
@property (readonly) BOOL allowSpecialFloatingPointValues __attribute__((swift_name("allowSpecialFloatingPointValues")));
@property (readonly) BOOL allowStructuredMapKeys __attribute__((swift_name("allowStructuredMapKeys")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL allowTrailingComma __attribute__((swift_name("allowTrailingComma")));
@property (readonly) NSString *classDiscriminator __attribute__((swift_name("classDiscriminator")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *classDiscriminatorMode __attribute__((swift_name("classDiscriminatorMode")));
@property (readonly) BOOL coerceInputValues __attribute__((swift_name("coerceInputValues")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL decodeEnumsCaseInsensitive __attribute__((swift_name("decodeEnumsCaseInsensitive")));
@property (readonly) BOOL encodeDefaults __attribute__((swift_name("encodeDefaults")));
@property (readonly) BOOL explicitNulls __attribute__((swift_name("explicitNulls")));
@property (readonly) BOOL ignoreUnknownKeys __attribute__((swift_name("ignoreUnknownKeys")));
@property (readonly) BOOL isLenient __attribute__((swift_name("isLenient")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) id<VFWKMPKotlinx_serialization_jsonJsonNamingStrategy> _Nullable namingStrategy __attribute__((swift_name("namingStrategy")));
@property (readonly) BOOL prettyPrint __attribute__((swift_name("prettyPrint")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSString *prettyPrintIndent __attribute__((swift_name("prettyPrintIndent")));
@property (readonly) BOOL useAlternativeNames __attribute__((swift_name("useAlternativeNames")));
@property (readonly) BOOL useArrayPolymorphism __attribute__((swift_name("useArrayPolymorphism")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol VFWKMPKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol VFWKMPKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<VFWKMPKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol VFWKMPKotlinAnnotation
@required
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface VFWKMPKotlinx_serialization_coreSerialKind : VFWKMPBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol VFWKMPKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<VFWKMPKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) VFWKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface VFWKMPKotlinNothing : VFWKMPBase
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol VFWKMPKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<VFWKMPKotlinKClass>)kClass provider:(id<VFWKMPKotlinx_serialization_coreKSerializer> (^)(NSArray<id<VFWKMPKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<VFWKMPKotlinKClass>)kClass serializer:(id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<VFWKMPKotlinKClass>)baseClass actualClass:(id<VFWKMPKotlinKClass>)actualClass actualSerializer:(id<VFWKMPKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<VFWKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<VFWKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<VFWKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<VFWKMPKotlinKClass>)baseClass defaultSerializerProvider:(id<VFWKMPKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol VFWKMPKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol VFWKMPKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol VFWKMPKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol VFWKMPKotlinKClass <VFWKMPKotlinKDeclarationContainer, VFWKMPKotlinKAnnotatedElement, VFWKMPKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface VFWKMPKotlinx_serialization_jsonJsonElementCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<VFWKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol VFWKMPKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface VFWKMPKotlinEnum<E> : VFWKMPBase <VFWKMPKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) VFWKMPKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonClassDiscriminatorMode")))
@interface VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode : VFWKMPKotlinEnum<VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *none __attribute__((swift_name("none")));
@property (class, readonly) VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *allJsonObjects __attribute__((swift_name("allJsonObjects")));
@property (class, readonly) VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *polymorphic __attribute__((swift_name("polymorphic")));
+ (VFWKMPKotlinArray<VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<VFWKMPKotlinx_serialization_jsonClassDiscriminatorMode *> *entries __attribute__((swift_name("entries")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonNamingStrategy")))
@protocol VFWKMPKotlinx_serialization_jsonJsonNamingStrategy
@required
- (NSString *)serialNameForJsonDescriptor:(id<VFWKMPKotlinx_serialization_coreSerialDescriptor>)descriptor elementIndex:(int32_t)elementIndex serialName:(NSString *)serialName __attribute__((swift_name("serialNameForJson(descriptor:elementIndex:serialName:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface VFWKMPKotlinEnumCompanion : VFWKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) VFWKMPKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
