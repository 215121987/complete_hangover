package com.hangover.java.notification;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/23/16
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
public class FCMConfig {

    private Collection<String> multicast;
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, Object> notificationAttributes;

    public FCMConfig() {
        clear();
    }

    public String toJSON() {

        JSONObject obj = new JSONObject();
        JSONObject not = new JSONObject();
        not.putAll(notificationAttributes);
        obj.put("notification", not);
        obj.putAll(requestAttributes);
        if (!multicast.isEmpty()) {
            JSONArray ids = new JSONArray();
            ids.addAll(multicast);
            obj.put("registration_ids", ids);
        }
        return obj.toString();
    }

    public FCMConfig clear() {
        clearTargets();
        clearAttributes();
        return this;
    }

    public FCMConfig clearTargets() {
        multicast = new ArrayList<String>();
        return this;
    }

    public FCMConfig clearAttributes() {
        notificationAttributes = new HashMap<String, Object>();
        requestAttributes = new HashMap<String, Object>();
        return this;
    }

    public FCMConfig addNotificationAttribute(String key, Object value) {
        notificationAttributes.put(key, value);
        return this;
    }

    public FCMConfig addRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
        return this;
    }

    public FCMConfig registration_ids(Collection<String> targets) {
        this.multicast = targets;
        return this;
    }

    public FCMConfig addAllMulticasts(Collection<String> targets) {
        this.multicast.addAll(targets);
        return this;
    }

    public FCMConfig addMulticast(String target) {
        this.multicast.add(target);
        return this;
    }

    public FCMConfig to(String to) {
        return addRequestAttribute("to", to);
    }

    public FCMConfig condition(String cond) {
        return addRequestAttribute("condition", cond);
    }

    public FCMConfig collapse_key(String key) {
        return addRequestAttribute("collapse_key", key);
    }

    public FCMConfig priority(Integer p) {
        p = Math.min(p, 10);
        p = Math.max(p, 0);
        return addRequestAttribute("priority", p);
    }

    public FCMConfig delay_while_idle(Boolean b) {
        return addRequestAttribute("delay_while_idle", b);
    }

    public FCMConfig time_to_live(Integer n) {
        return addRequestAttribute("time_to_live", n);
    }

    public FCMConfig restricted_package_name(String name) {
        return addRequestAttribute("restricted_package_name", name);
    }

    public FCMConfig dry_run(Boolean b) {
        return addRequestAttribute("dry_run", b);
    }

    public FCMConfig data(Map<String, String> data) {
        JSONObject obj = new JSONObject();
        obj.putAll(data);
        return addRequestAttribute("data", obj);
    }

    public FCMConfig notification(Map<String, Object> map) {
        JSONObject obj = new JSONObject();
        obj.putAll(map);
        return addRequestAttribute("notification", obj);
    }

    public FCMConfig title(String title) {
        return addNotificationAttribute("title", title);
    }

    public FCMConfig body(String body) {
        return addNotificationAttribute("body", body);
    }

    public FCMConfig text(String text) {
        return body(text);
    }

    public FCMConfig icon(String ic) {
        return addNotificationAttribute("icon", ic);
    }

    public FCMConfig sound(String sound) {
        return addNotificationAttribute("sound", sound);
    }

    public FCMConfig tag(String tag) {
        return addNotificationAttribute("tag", tag);
    }

    public FCMConfig color(String rgb) {
        return addNotificationAttribute("color", rgb);
    }

    public FCMConfig click_action(String intent_filter) {
        return addNotificationAttribute("click_action", intent_filter);
    }

    public FCMConfig body_loc_key(String key) {
        return addNotificationAttribute("body_loc_key", key);
    }

    public FCMConfig body_loc_args(Collection<String> args) {
        JSONArray arr = new JSONArray();
        arr.addAll(args);
        return addNotificationAttribute("body_loc_key", arr);
    }

    public FCMConfig title_loc_key(String key) {
        return addNotificationAttribute("title_loc_key", key);
    }

    public FCMConfig title_loc_args(Collection<String> args) {
        JSONArray arr = new JSONArray();
        arr.addAll(args);
        return addNotificationAttribute("title_loc_key", arr);
    }


}
