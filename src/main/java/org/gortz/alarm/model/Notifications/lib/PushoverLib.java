package org.gortz.alarm.model.Notifications.lib;

/**
 * Created by jimmy on 4/24/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import javax.net.ssl.HttpsURLConnection;

public class PushoverLib {

    /**
     * The application token from PushoverLib.net's application section.
     */
    private String appToken;

    /**
     * The user token, from a user's user key page.
     */
    private String userToken;

    /**
     * The device, an optional field that allows a user to specify a device on the account to which the message should be sent.
     */
    private String device;

    /**
     * Initializes a PushoverLib object for talking to PushoverLib.
     *
     * @param appToken  Your application token, generated from PushoverLib.net
     * @param userToken A user's usertoken, found on the user page from PushoverLib.net
     */
    public PushoverLib(String appToken, String userToken) {
        this.appToken = appToken;
        this.userToken = userToken;
    }

    /**
     * Initializes a PushoverLib object for talking to PushoverLib.
     *
     * @param appToken  Your application token, generated from PushoverLib.net
     * @param userToken A user's usertoken, found on the user page from PushoverLib.net
     * @param device    The device to send the message to.
     */
    public PushoverLib(String appToken, String userToken, String device) {
        this.appToken = appToken;
        this.userToken = userToken;
        this.device = device;
    }

    /**
     * Gets the application token associated with this object
     *
     * @return appToken The application token
     */
    public String getAppToken() {
        return appToken;
    }

    /**
     * Sets a new application token for interfacing with PushoverLib. All further requests will be sent using this app token.
     *
     * @param appToken
     */
    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    /**
     * Gets the user token associated with this object
     *
     * @return userToken
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * Sets a new user token for interfacing with PushoverLib. All further requests will be sent using this user token.
     *
     * @param userToken
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * Gets the user's device.
     *
     * @return device
     */
    public String getDevice() {
        return device;
    }

    /**
     * Sets the user's destination device.
     *
     * @param device The device name as set by the user in PushoverLib
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * Sends a high priority notification.
     *
     * @param message The message to send
     * @param title   The title.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessageHighPriority(String message, String title) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&priority=1");
    }

    /**
     * Sends a high priority notification.
     *
     * @param message The message to send.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessageHighPriority(String message) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&priority=1");
    }

    /**
     * Sends a high priority notification.
     *
     * @param message  The message to send.
     * @param title    The title.
     * @param url      A URL.
     * @param urlTitle A URL title.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessageHighPriority(String message, String title, String url, String urlTitle) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8")
                + "&url_title=" + URLEncoder.encode(urlTitle, "UTF-8") + "&priority=1");
    }

    /**
     * Sends a high priority notification.
     *
     * @param message The message to send.
     * @param title   The title.
     * @param url     A URL.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessageHighPriority(String message, String title, String url) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8") + "&priority=1");
    }

    /**
     * Sends a message.
     *
     * @param message  The message to send.
     * @param title    The title.
     * @param url      A URL.
     * @param urlTitle A URL title.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessage(String message, String title, String url, String urlTitle) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8")
                + "&url_title=" + URLEncoder.encode(urlTitle, "UTF-8"));
    }

    /**
     * Sends a message.
     *
     * @param message The message to send.
     * @param title   The title.
     * @param url     A URL.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessage(String message, String title, String url) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8"));
    }

    /**
     * Sends a message.
     *
     * @param message The message to send.
     * @param title   The title.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessage(String message, String title) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8"));
    }

    /**
     * Sends a message.
     *
     * @param message The message to send.
     * @return JSON reply from PushoverLib.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public String sendMessage(String message) throws UnsupportedEncodingException, IOException {
        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8"));
    }

    /**
     * Gets a string with the auth tokens already made.
     *
     * @return String of auth tokens
     * @throws UnsupportedEncodingException
     */
    private String getAuthenticationTokens() throws UnsupportedEncodingException {

        if (device != null) {
            if (!(device.trim() == "")) {
                return "token=" + getAppToken() + "&user=" + getUserToken() + "&device=" + getDevice();
            }
        }

        return "token=" + getAppToken() + "&user=" + getUserToken();
    }

    /**
     * Sends a raw bit of text via POST to PushoverLib.
     *
     * @param rawMessage
     * @return JSON reply from PushoverLib.
     * @throws IOException
     */
    private String sendToPushoverRaw(String rawMessage) throws IOException {
        URL pushoverUrl = new URL("https://api.pushover.net/1/messages.json");

        HttpsURLConnection connection = (HttpsURLConnection) pushoverUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(rawMessage.getBytes(Charset.forName("UTF-8")));
        outputStream.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output = "";
        String outputCache = "";
        while ((outputCache = br.readLine()) != null) {
            output += outputCache;
        }
        br.close();
        return output;
    }
}