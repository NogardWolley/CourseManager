package xmu.crms.service;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xmu.crms.entity.FixGroup;
import xmu.crms.exception.FixGroupNotFoundException;
import xmu.crms.exception.UserNotFoundException;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * @Author:YellowDragon
 * @Description:
 * @Date:Created in 22:05 2017/12/26 0026
 * @Modified By:
 */
public class FixGroupServiceTest extends TestCase {
    @Autowired
    private FixGroupService fixGroupService;

    @Test
    public void testgetFixedGroupById() throws FixGroupNotFoundException{
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(new BigInteger("3"), new BigInteger("1"));
            System.out.println(fixGroup.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}