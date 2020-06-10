//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.videoio;

import org.opencv.core.Mat;

// C++: class VideoCapture
/**
 * Class for video capturing from video files, image sequences or cameras.
 *
 * The class provides C++ API for capturing video from cameras or for reading video files and image sequences.
 *
 * Here is how the class can be used:
 * INCLUDE: samples/cpp/videocapture_basic.cpp
 *
 * <b>Note:</b> In REF: videoio_c "C API" the black-box structure {@code CvCapture} is used instead of %VideoCapture.
 * <b>Note:</b>
 * <ul>
 *   <li>
 *    (C++) A basic sample on using the %VideoCapture interface can be found at
 *     {@code OPENCV_SOURCE_CODE/samples/cpp/videocapture_starter.cpp}
 *   </li>
 *   <li>
 *    (Python) A basic sample on using the %VideoCapture interface can be found at
 *     {@code OPENCV_SOURCE_CODE/samples/python/video.py}
 *   </li>
 *   <li>
 *    (Python) A multi threaded video processing sample can be found at
 *     {@code OPENCV_SOURCE_CODE/samples/python/video_threaded.py}
 *   </li>
 *   <li>
 *    (Python) %VideoCapture sample showcasing some features of the Video4Linux2 backend
 *     {@code OPENCV_SOURCE_CODE/samples/python/video_v4l2.py}
 *   </li>
 * </ul>
 */
public class VideoCapture {

    protected final long nativeObj;
    protected VideoCapture(long addr) { nativeObj = addr; }

    public long getNativeObjAddr() { return nativeObj; }

    // internal usage only
    public static VideoCapture __fromPtr__(long addr) { return new VideoCapture(addr); }

    //
    // C++:   cv::VideoCapture::VideoCapture(String filename, int apiPreference)
    //

    /**
     *
     *      Open video file or a capturing device or a IP video stream for video capturing with API Preference
     *
     *     @param filename it can be:
     * <ul>
     *   <li>
     *      name of video file (eg. {@code video.avi})
     *   </li>
     *   <li>
     *      or image sequence (eg. {@code img_%02d.jpg}, which will read samples like {@code img_00.jpg, img_01.jpg, img_02.jpg, ...})
     *   </li>
     *   <li>
     *      or URL of video stream (eg. {@code protocol://host:port/script_name?script_params|auth}).
     *       Note that each video stream or IP camera feed has its own URL scheme. Please refer to the
     *       documentation of source stream to know the right URL.
     *     @param apiPreference preferred Capture API backends to use. Can be used to enforce a specific reader
     *     implementation if multiple are available: e.g. cv::CAP_FFMPEG or cv::CAP_IMAGES or cv::CAP_DSHOW.
     *     SEE: The list of supported API backends cv::VideoCaptureAPIs
     *   </li>
     * </ul>
     */
    public VideoCapture(String filename, int apiPreference) {
        nativeObj = VideoCapture_0(filename, apiPreference);
    }


    //
    // C++:   cv::VideoCapture::VideoCapture(String filename)
    //

    /**
     *
     *      Open video file or a capturing device or a IP video stream for video capturing
     *
     *     Same as VideoCapture(const String&amp; filename, int apiPreference) but using default Capture API backends
     * @param filename automatically generated
     */
    public VideoCapture(String filename) {
        nativeObj = VideoCapture_1(filename);
    }


    //
    // C++:   cv::VideoCapture::VideoCapture(int index, int apiPreference)
    //

    /**
     *
     *      Opens a camera for video capturing
     *
     *     @param index id of the video capturing device to open. To open default camera using default backend just pass 0.
     *     (to backward compatibility usage of camera_id + domain_offset (CAP_*) is valid when apiPreference is CAP_ANY)
     *     @param apiPreference preferred Capture API backends to use. Can be used to enforce a specific reader
     *     implementation if multiple are available: e.g. cv::CAP_DSHOW or cv::CAP_MSMF or cv::CAP_V4L2.
     *
     *     SEE: The list of supported API backends cv::VideoCaptureAPIs
     */
    public VideoCapture(int index, int apiPreference) {
        nativeObj = VideoCapture_2(index, apiPreference);
    }


    //
    // C++:   cv::VideoCapture::VideoCapture(int index)
    //

    /**
     *
     *      Open a camera for video capturing
     *
     *     @param index camera_id + domain_offset (CAP_*) id of the video capturing device to open. To open default camera using default backend just pass 0.
     *     Use a {@code domain_offset} to enforce a specific reader implementation if multiple are available like cv::CAP_FFMPEG or cv::CAP_IMAGES or cv::CAP_DSHOW.
     *     e.g. to open Camera 1 using the MS Media Foundation API use {@code index = 1 + cv::CAP_MSMF}
     *
     *     SEE: The list of supported API backends cv::VideoCaptureAPIs
     */
    public VideoCapture(int index) {
        nativeObj = VideoCapture_3(index);
    }


    //
    // C++:   cv::VideoCapture::VideoCapture()
    //

    /**
     * Default constructor
     *     <b>Note:</b> In REF: videoio_c "C API", when you finished working with video, release CvCapture structure with
     *     cvReleaseCapture(), or use Ptr&lt;CvCapture&gt; that calls cvReleaseCapture() automatically in the
     *     destructor.
     */
    public VideoCapture() {
        nativeObj = VideoCapture_4();
    }


    //
    // C++:  String cv::VideoCapture::getBackendName()
    //

    /**
     * Returns used backend API name
     *
     *      <b>Note:</b> Stream should be opened.
     * @return automatically generated
     */
    public String getBackendName() {
        return getBackendName_0(nativeObj);
    }


    //
    // C++:  bool cv::VideoCapture::grab()
    //

    /**
     * Grabs the next frame from video file or capturing device.
     *
     *     @return {@code true} (non-zero) in the case of success.
     *
     *     The method/function grabs the next frame from video file or camera and returns true (non-zero) in
     *     the case of success.
     *
     *     The primary use of the function is in multi-camera environments, especially when the cameras do not
     *     have hardware synchronization. That is, you call VideoCapture::grab() for each camera and after that
     *     call the slower method VideoCapture::retrieve() to decode and get frame from each camera. This way
     *     the overhead on demosaicing or motion jpeg decompression etc. is eliminated and the retrieved frames
     *     from different cameras will be closer in time.
     *
     *     Also, when a connected camera is multi-head (for example, a stereo camera or a Kinect device), the
     *     correct way of retrieving data from it is to call VideoCapture::grab() first and then call
     *     VideoCapture::retrieve() one or more times with different values of the channel parameter.
     *
     *     REF: tutorial_kinect_openni
     */
    public boolean grab() {
        return grab_0(nativeObj);
    }


    //
    // C++:  bool cv::VideoCapture::isOpened()
    //

    /**
     * Returns true if video capturing has been initialized already.
     *
     *     If the previous call to VideoCapture constructor or VideoCapture::open() succeeded, the method returns
     *     true.
     * @return automatically generated
     */
    public boolean isOpened() {
        return isOpened_0(nativeObj);
    }


    //
    // C++:  bool cv::VideoCapture::open(String filename, int apiPreference)
    //

    /**
     * Open video file or a capturing device or a IP video stream for video capturing with API Preference
     *
     *     
     *
     *     Parameters are same as the constructor VideoCapture(const String&amp; filename, int apiPreference)
     *     @return {@code true} if the file has been successfully opened
     *
     *     The method first calls VideoCapture::release to close the already opened file or camera.
     * @param filename automatically generated
     * @param apiPreference automatically generated
     */
    public boolean open(String filename, int apiPreference) {
        return open_0(nativeObj, filename, apiPreference);
    }


    //
    // C++:  bool cv::VideoCapture::open(String filename)
    //

    /**
     *  Open video file or a capturing device or a IP video stream for video capturing
     *
     *     
     *
     *     Parameters are same as the constructor VideoCapture(const String&amp; filename)
     *     @return {@code true} if the file has been successfully opened
     *
     *     The method first calls VideoCapture::release to close the already opened file or camera.
     * @param filename automatically generated
     */
    public boolean open(String filename) {
        return open_1(nativeObj, filename);
    }


    //
    // C++:  bool cv::VideoCapture::open(int cameraNum, int apiPreference)
    //

    /**
     *  Open a camera for video capturing
     *
     *     
     *
     *     Parameters are similar as the constructor VideoCapture(int index),except it takes an additional argument apiPreference.
     *     Definitely, is same as open(int index) where {@code index=cameraNum + apiPreference}
     *     @return {@code true} if the camera has been successfully opened.
     * @param cameraNum automatically generated
     * @param apiPreference automatically generated
     */
    public boolean open(int cameraNum, int apiPreference) {
        return open_2(nativeObj, cameraNum, apiPreference);
    }


    //
    // C++:  bool cv::VideoCapture::open(int index)
    //

    /**
     *  Open a camera for video capturing
     *
     *     
     *
     *     Parameters are same as the constructor VideoCapture(int index)
     *     @return {@code true} if the camera has been successfully opened.
     *
     *     The method first calls VideoCapture::release to close the already opened file or camera.
     * @param index automatically generated
     */
    public boolean open(int index) {
        return open_3(nativeObj, index);
    }


    //
    // C++:  bool cv::VideoCapture::read(Mat& image)
    //

    /**
     * Grabs, decodes and returns the next video frame.
     *
     *     @return {@code false} if no frames has been grabbed
     *
     *     The method/function combines VideoCapture::grab() and VideoCapture::retrieve() in one call. This is the
     *     most convenient method for reading video files or capturing data from decode and returns the just
     *     grabbed frame. If no frames has been grabbed (camera has been disconnected, or there are no more
     *     frames in video file), the method returns false and the function returns empty image (with %cv::Mat, test it with Mat::empty()).
     *
     *     <b>Note:</b> In REF: videoio_c "C API", functions cvRetrieveFrame() and cv.RetrieveFrame() return image stored inside the video
     *     capturing structure. It is not allowed to modify or release the image! You can copy the frame using
     *     cvCloneImage and then do whatever you want with the copy.
     * @param image automatically generated
     */
    public boolean read(Mat image) {
        return read_0(nativeObj, image.nativeObj);
    }


    //
    // C++:  bool cv::VideoCapture::retrieve(Mat& image, int flag = 0)
    //

    /**
     * Decodes and returns the grabbed video frame.
     *
     *     @param flag it could be a frame index or a driver specific flag
     *     @return {@code false} if no frames has been grabbed
     *
     *     The method decodes and returns the just grabbed frame. If no frames has been grabbed
     *     (camera has been disconnected, or there are no more frames in video file), the method returns false
     *     and the function returns an empty image (with %cv::Mat, test it with Mat::empty()).
     *
     *     SEE: read()
     *
     *     <b>Note:</b> In REF: videoio_c "C API", functions cvRetrieveFrame() and cv.RetrieveFrame() return image stored inside the video
     *     capturing structure. It is not allowed to modify or release the image! You can copy the frame using
     *     cvCloneImage and then do whatever you want with the copy.
     * @param image automatically generated
     */
    public boolean retrieve(Mat image, int flag) {
        return retrieve_0(nativeObj, image.nativeObj, flag);
    }

    /**
     * Decodes and returns the grabbed video frame.
     *
     *     @return {@code false} if no frames has been grabbed
     *
     *     The method decodes and returns the just grabbed frame. If no frames has been grabbed
     *     (camera has been disconnected, or there are no more frames in video file), the method returns false
     *     and the function returns an empty image (with %cv::Mat, test it with Mat::empty()).
     *
     *     SEE: read()
     *
     *     <b>Note:</b> In REF: videoio_c "C API", functions cvRetrieveFrame() and cv.RetrieveFrame() return image stored inside the video
     *     capturing structure. It is not allowed to modify or release the image! You can copy the frame using
     *     cvCloneImage and then do whatever you want with the copy.
     * @param image automatically generated
     */
    public boolean retrieve(Mat image) {
        return retrieve_1(nativeObj, image.nativeObj);
    }


    //
    // C++:  bool cv::VideoCapture::set(int propId, double value)
    //

    /**
     * Sets a property in the VideoCapture.
     *
     *     @param propId Property identifier from cv::VideoCaptureProperties (eg. cv::CAP_PROP_POS_MSEC, cv::CAP_PROP_POS_FRAMES, ...)
     *     or one from REF: videoio_flags_others
     *     @param value Value of the property.
     *     @return {@code true} if the property is supported by backend used by the VideoCapture instance.
     *     <b>Note:</b> Even if it returns {@code true} this doesn't ensure that the property
     *     value has been accepted by the capture device. See note in VideoCapture::get()
     */
    public boolean set(int propId, double value) {
        return set_0(nativeObj, propId, value);
    }


    //
    // C++:  double cv::VideoCapture::get(int propId)
    //

    /**
     * Returns the specified VideoCapture property
     *
     *     @param propId Property identifier from cv::VideoCaptureProperties (eg. cv::CAP_PROP_POS_MSEC, cv::CAP_PROP_POS_FRAMES, ...)
     *     or one from REF: videoio_flags_others
     *     @return Value for the specified property. Value 0 is returned when querying a property that is
     *     not supported by the backend used by the VideoCapture instance.
     *
     *     <b>Note:</b> Reading / writing properties involves many layers. Some unexpected result might happens
     *     along this chain.
     *     <code>
     *     VideoCapture -&gt; API Backend -&gt; Operating System -&gt; Device Driver -&gt; Device Hardware
     *     </code>
     *     The returned value might be different from what really used by the device or it could be encoded
     *     using device dependent rules (eg. steps or percentage). Effective behaviour depends from device
     *     driver and API Backend
     */
    public double get(int propId) {
        return get_0(nativeObj, propId);
    }


    //
    // C++:  void cv::VideoCapture::release()
    //

    /**
     * Closes video file or capturing device.
     *
     *     The method is automatically called by subsequent VideoCapture::open and by VideoCapture
     *     destructor.
     *
     *     The C function also deallocates memory and clears \*capture pointer.
     */
    public void release() {
        release_0(nativeObj);
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++:   cv::VideoCapture::VideoCapture(String filename, int apiPreference)
    private static native long VideoCapture_0(String filename, int apiPreference);

    // C++:   cv::VideoCapture::VideoCapture(String filename)
    private static native long VideoCapture_1(String filename);

    // C++:   cv::VideoCapture::VideoCapture(int index, int apiPreference)
    private static native long VideoCapture_2(int index, int apiPreference);

    // C++:   cv::VideoCapture::VideoCapture(int index)
    private static native long VideoCapture_3(int index);

    // C++:   cv::VideoCapture::VideoCapture()
    private static native long VideoCapture_4();

    // C++:  String cv::VideoCapture::getBackendName()
    private static native String getBackendName_0(long nativeObj);

    // C++:  bool cv::VideoCapture::grab()
    private static native boolean grab_0(long nativeObj);

    // C++:  bool cv::VideoCapture::isOpened()
    private static native boolean isOpened_0(long nativeObj);

    // C++:  bool cv::VideoCapture::open(String filename, int apiPreference)
    private static native boolean open_0(long nativeObj, String filename, int apiPreference);

    // C++:  bool cv::VideoCapture::open(String filename)
    private static native boolean open_1(long nativeObj, String filename);

    // C++:  bool cv::VideoCapture::open(int cameraNum, int apiPreference)
    private static native boolean open_2(long nativeObj, int cameraNum, int apiPreference);

    // C++:  bool cv::VideoCapture::open(int index)
    private static native boolean open_3(long nativeObj, int index);

    // C++:  bool cv::VideoCapture::read(Mat& image)
    private static native boolean read_0(long nativeObj, long image_nativeObj);

    // C++:  bool cv::VideoCapture::retrieve(Mat& image, int flag = 0)
    private static native boolean retrieve_0(long nativeObj, long image_nativeObj, int flag);
    private static native boolean retrieve_1(long nativeObj, long image_nativeObj);

    // C++:  bool cv::VideoCapture::set(int propId, double value)
    private static native boolean set_0(long nativeObj, int propId, double value);

    // C++:  double cv::VideoCapture::get(int propId)
    private static native double get_0(long nativeObj, int propId);

    // C++:  void cv::VideoCapture::release()
    private static native void release_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
