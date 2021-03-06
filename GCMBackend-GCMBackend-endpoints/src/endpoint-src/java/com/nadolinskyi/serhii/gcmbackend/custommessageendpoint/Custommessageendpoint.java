/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-06-26 16:27:34 UTC)
 * on 2013-07-11 at 11:38:41 UTC 
 * Modify at your own risk.
 */

package com.nadolinskyi.serhii.gcmbackend.custommessageendpoint;

/**
 * Service definition for Custommessageendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link CustommessageendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Custommessageendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.15.0-rc of the  library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://peerless-text-265.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "custommessageendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Custommessageendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Custommessageendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getCustomMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link GetCustomMessage#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetCustomMessage getCustomMessage(java.lang.Long id) throws java.io.IOException {
    GetCustomMessage result = new GetCustomMessage(id);
    initialize(result);
    return result;
  }

  public class GetCustomMessage extends CustommessageendpointRequest<com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage> {

    private static final String REST_PATH = "custommessage/{id}";

    /**
     * Create a request for the method "getCustomMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link GetCustomMessage#execute()} method to invoke
     * the remote operation. <p> {@link GetCustomMessage#initialize(com.google.api.client.googleapis.s
     * ervices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetCustomMessage(java.lang.Long id) {
      super(Custommessageendpoint.this, "GET", REST_PATH, null, com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetCustomMessage setAlt(java.lang.String alt) {
      return (GetCustomMessage) super.setAlt(alt);
    }

    @Override
    public GetCustomMessage setFields(java.lang.String fields) {
      return (GetCustomMessage) super.setFields(fields);
    }

    @Override
    public GetCustomMessage setKey(java.lang.String key) {
      return (GetCustomMessage) super.setKey(key);
    }

    @Override
    public GetCustomMessage setOauthToken(java.lang.String oauthToken) {
      return (GetCustomMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public GetCustomMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetCustomMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetCustomMessage setQuotaUser(java.lang.String quotaUser) {
      return (GetCustomMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetCustomMessage setUserIp(java.lang.String userIp) {
      return (GetCustomMessage) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetCustomMessage setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetCustomMessage set(String parameterName, Object value) {
      return (GetCustomMessage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertCustomMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link InsertCustomMessage#execute()} method to invoke the
   * remote operation.
   *
   * @param content the {@link com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage}
   * @return the request
   */
  public InsertCustomMessage insertCustomMessage(com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage content) throws java.io.IOException {
    InsertCustomMessage result = new InsertCustomMessage(content);
    initialize(result);
    return result;
  }

  public class InsertCustomMessage extends CustommessageendpointRequest<com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage> {

    private static final String REST_PATH = "custommessage";

    /**
     * Create a request for the method "insertCustomMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link InsertCustomMessage#execute()} method to
     * invoke the remote operation. <p> {@link InsertCustomMessage#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage}
     * @since 1.13
     */
    protected InsertCustomMessage(com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage content) {
      super(Custommessageendpoint.this, "POST", REST_PATH, content, com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage.class);
    }

    @Override
    public InsertCustomMessage setAlt(java.lang.String alt) {
      return (InsertCustomMessage) super.setAlt(alt);
    }

    @Override
    public InsertCustomMessage setFields(java.lang.String fields) {
      return (InsertCustomMessage) super.setFields(fields);
    }

    @Override
    public InsertCustomMessage setKey(java.lang.String key) {
      return (InsertCustomMessage) super.setKey(key);
    }

    @Override
    public InsertCustomMessage setOauthToken(java.lang.String oauthToken) {
      return (InsertCustomMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertCustomMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertCustomMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertCustomMessage setQuotaUser(java.lang.String quotaUser) {
      return (InsertCustomMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertCustomMessage setUserIp(java.lang.String userIp) {
      return (InsertCustomMessage) super.setUserIp(userIp);
    }

    @Override
    public InsertCustomMessage set(String parameterName, Object value) {
      return (InsertCustomMessage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listCustomMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link ListCustomMessage#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListCustomMessage listCustomMessage() throws java.io.IOException {
    ListCustomMessage result = new ListCustomMessage();
    initialize(result);
    return result;
  }

  public class ListCustomMessage extends CustommessageendpointRequest<com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CollectionResponseCustomMessage> {

    private static final String REST_PATH = "custommessage";

    /**
     * Create a request for the method "listCustomMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link ListCustomMessage#execute()} method to invoke
     * the remote operation. <p> {@link ListCustomMessage#initialize(com.google.api.client.googleapis.
     * services.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListCustomMessage() {
      super(Custommessageendpoint.this, "GET", REST_PATH, null, com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CollectionResponseCustomMessage.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListCustomMessage setAlt(java.lang.String alt) {
      return (ListCustomMessage) super.setAlt(alt);
    }

    @Override
    public ListCustomMessage setFields(java.lang.String fields) {
      return (ListCustomMessage) super.setFields(fields);
    }

    @Override
    public ListCustomMessage setKey(java.lang.String key) {
      return (ListCustomMessage) super.setKey(key);
    }

    @Override
    public ListCustomMessage setOauthToken(java.lang.String oauthToken) {
      return (ListCustomMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public ListCustomMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListCustomMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListCustomMessage setQuotaUser(java.lang.String quotaUser) {
      return (ListCustomMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListCustomMessage setUserIp(java.lang.String userIp) {
      return (ListCustomMessage) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListCustomMessage setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListCustomMessage setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListCustomMessage set(String parameterName, Object value) {
      return (ListCustomMessage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeCustomMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link RemoveCustomMessage#execute()} method to invoke the
   * remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveCustomMessage removeCustomMessage(java.lang.Long id) throws java.io.IOException {
    RemoveCustomMessage result = new RemoveCustomMessage(id);
    initialize(result);
    return result;
  }

  public class RemoveCustomMessage extends CustommessageendpointRequest<com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage> {

    private static final String REST_PATH = "custommessage/{id}";

    /**
     * Create a request for the method "removeCustomMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link RemoveCustomMessage#execute()} method to
     * invoke the remote operation. <p> {@link RemoveCustomMessage#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveCustomMessage(java.lang.Long id) {
      super(Custommessageendpoint.this, "DELETE", REST_PATH, null, com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveCustomMessage setAlt(java.lang.String alt) {
      return (RemoveCustomMessage) super.setAlt(alt);
    }

    @Override
    public RemoveCustomMessage setFields(java.lang.String fields) {
      return (RemoveCustomMessage) super.setFields(fields);
    }

    @Override
    public RemoveCustomMessage setKey(java.lang.String key) {
      return (RemoveCustomMessage) super.setKey(key);
    }

    @Override
    public RemoveCustomMessage setOauthToken(java.lang.String oauthToken) {
      return (RemoveCustomMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveCustomMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveCustomMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveCustomMessage setQuotaUser(java.lang.String quotaUser) {
      return (RemoveCustomMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveCustomMessage setUserIp(java.lang.String userIp) {
      return (RemoveCustomMessage) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveCustomMessage setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveCustomMessage set(String parameterName, Object value) {
      return (RemoveCustomMessage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "sendMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link SendMessage#execute()} method to invoke the remote
   * operation.
   *
   * @param nickname
   * @param message
   * @return the request
   */
  public SendMessage sendMessage(java.lang.String nickname, java.lang.String message) throws java.io.IOException {
    SendMessage result = new SendMessage(nickname, message);
    initialize(result);
    return result;
  }

  public class SendMessage extends CustommessageendpointRequest<Void> {

    private static final String REST_PATH = "sendMessage/{nickname}/{message}";

    /**
     * Create a request for the method "sendMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link SendMessage#execute()} method to invoke the
     * remote operation. <p> {@link
     * SendMessage#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param nickname
     * @param message
     * @since 1.13
     */
    protected SendMessage(java.lang.String nickname, java.lang.String message) {
      super(Custommessageendpoint.this, "POST", REST_PATH, null, Void.class);
      this.nickname = com.google.api.client.util.Preconditions.checkNotNull(nickname, "Required parameter nickname must be specified.");
      this.message = com.google.api.client.util.Preconditions.checkNotNull(message, "Required parameter message must be specified.");
    }

    @Override
    public SendMessage setAlt(java.lang.String alt) {
      return (SendMessage) super.setAlt(alt);
    }

    @Override
    public SendMessage setFields(java.lang.String fields) {
      return (SendMessage) super.setFields(fields);
    }

    @Override
    public SendMessage setKey(java.lang.String key) {
      return (SendMessage) super.setKey(key);
    }

    @Override
    public SendMessage setOauthToken(java.lang.String oauthToken) {
      return (SendMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public SendMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (SendMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public SendMessage setQuotaUser(java.lang.String quotaUser) {
      return (SendMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public SendMessage setUserIp(java.lang.String userIp) {
      return (SendMessage) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String nickname;

    /**

     */
    public java.lang.String getNickname() {
      return nickname;
    }

    public SendMessage setNickname(java.lang.String nickname) {
      this.nickname = nickname;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String message;

    /**

     */
    public java.lang.String getMessage() {
      return message;
    }

    public SendMessage setMessage(java.lang.String message) {
      this.message = message;
      return this;
    }

    @Override
    public SendMessage set(String parameterName, Object value) {
      return (SendMessage) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateCustomMessage".
   *
   * This request holds the parameters needed by the the custommessageendpoint server.  After setting
   * any optional parameters, call the {@link UpdateCustomMessage#execute()} method to invoke the
   * remote operation.
   *
   * @param content the {@link com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage}
   * @return the request
   */
  public UpdateCustomMessage updateCustomMessage(com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage content) throws java.io.IOException {
    UpdateCustomMessage result = new UpdateCustomMessage(content);
    initialize(result);
    return result;
  }

  public class UpdateCustomMessage extends CustommessageendpointRequest<com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage> {

    private static final String REST_PATH = "custommessage";

    /**
     * Create a request for the method "updateCustomMessage".
     *
     * This request holds the parameters needed by the the custommessageendpoint server.  After
     * setting any optional parameters, call the {@link UpdateCustomMessage#execute()} method to
     * invoke the remote operation. <p> {@link UpdateCustomMessage#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage}
     * @since 1.13
     */
    protected UpdateCustomMessage(com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage content) {
      super(Custommessageendpoint.this, "PUT", REST_PATH, content, com.nadolinskyi.serhii.gcmbackend.custommessageendpoint.model.CustomMessage.class);
    }

    @Override
    public UpdateCustomMessage setAlt(java.lang.String alt) {
      return (UpdateCustomMessage) super.setAlt(alt);
    }

    @Override
    public UpdateCustomMessage setFields(java.lang.String fields) {
      return (UpdateCustomMessage) super.setFields(fields);
    }

    @Override
    public UpdateCustomMessage setKey(java.lang.String key) {
      return (UpdateCustomMessage) super.setKey(key);
    }

    @Override
    public UpdateCustomMessage setOauthToken(java.lang.String oauthToken) {
      return (UpdateCustomMessage) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateCustomMessage setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateCustomMessage) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateCustomMessage setQuotaUser(java.lang.String quotaUser) {
      return (UpdateCustomMessage) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateCustomMessage setUserIp(java.lang.String userIp) {
      return (UpdateCustomMessage) super.setUserIp(userIp);
    }

    @Override
    public UpdateCustomMessage set(String parameterName, Object value) {
      return (UpdateCustomMessage) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Custommessageendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Custommessageendpoint}. */
    @Override
    public Custommessageendpoint build() {
      return new Custommessageendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link CustommessageendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCustommessageendpointRequestInitializer(
        CustommessageendpointRequestInitializer custommessageendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(custommessageendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
