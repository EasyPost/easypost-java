package com.easypost;

import com.easypost.model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.lang.InterruptedException;

import com.easypost.exception.EasyPostException;

import static org.junit.Assert.*;

public class UserTest {

  @BeforeClass
  public static void setUp() {
    EasyPost.apiKey = "PROD KEY"; // PROD KEY REQUIRED
  }

  @Test
  public void testRetrieveMe() throws EasyPostException {
    User me = User.retrieveMe();

    assertNotNull(me.getName());
    assertNotNull(me.getEmail());
  }

  @Test
  public void testCreateChild() throws EasyPostException {
    User child = User.create();
    assertNotNull(child.getName());
    assertNotNull(child.getEmail());
    
    List<ApiKey> childKeys = child.apiKeys();
    assertNotNull(childKeys.get(0).getKey()); // one is test and one is prod
    assertNotNull(childKeys.get(1).getKey()); // check getMode()
  }

  @Test
  public void testEditUser() throws EasyPostException {
    User me = User.retrieveMe();

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("name", "New Name2736");
    me.update(params);

    assertEquals("User was not updated successfully", "New Name2736", me.getName());
    System.out.println(me.prettyPrint());
  }
}

