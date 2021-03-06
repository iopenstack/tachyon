package tachyon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for tachyon.UserInfo
 */
public class UserInfoTest {
  @Test
  public void constructorTest() {
    for (int k = 1; k <= 1000; k += 50) {
      UserInfo tUserInfo = new UserInfo(k);
      Assert.assertEquals(k, tUserInfo.getUserId());
    }
  }

  @Test(expected = RuntimeException.class)  
  public void constructorWithExceptionTest() {
    for (int k = 0; k >= -1000; k -= 50) {
      UserInfo tUserInfo = new UserInfo(k);
      Assert.assertEquals(k, tUserInfo.getUserId());
      Assert.fail("UserId " + k + " should be invalid.");
    }
  }

  @Test
  public void addOwnBytesTest() {
    UserInfo tUserInfo = new UserInfo(1);
    tUserInfo.addOwnBytes(7);
    tUserInfo.addOwnBytes(70);
    tUserInfo.addOwnBytes(700);
    tUserInfo.addOwnBytes(7000);
    Assert.assertEquals(7777, tUserInfo.getOwnBytes());
  }

  @Test
  public void getUserIdTest() {
    for (int k = 1; k < 1000; k += 66) {
      UserInfo tUserInfo = new UserInfo(k);
      Assert.assertEquals(k, tUserInfo.getUserId());
    }
  }

  @Test
  public void timeoutTest() {
    UserInfo tUserInfo = new UserInfo(1);
    Assert.assertFalse(tUserInfo.timeout());
  }

  @Test
  public void toStringTest() {
    UserInfo tUserInfo = new UserInfo(99);
    tUserInfo.addOwnBytes(2093);
    tUserInfo.addOwnBytes(- 1029);
    Assert.assertEquals("UserInfo( USER_ID: 99, mOwnBytes: 1064, mLastHeartbeatMs: ",
        tUserInfo.toString().substring(0, 58)); 
  }

  @Test
  public void generalTest() {
    for (int k = 1; k < 10; k ++) {
      UserInfo tUserInfo = new UserInfo(k);
      tUserInfo.addOwnBytes(3222 * k);
      tUserInfo.addOwnBytes(-1111 * k);
      Assert.assertEquals(2111 * k, tUserInfo.getOwnBytes());
    }
  }
}