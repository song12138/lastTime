package pro.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dao.ShiroDao;
import pro.entity.SysResource;
import pro.entity.SysRole;
import pro.entity.SysUser;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by paul on 2017/10/25.
 */
@Service
public class ShiroService {

    @Autowired
    private ShiroDao shiroDao;



    public SysUser findByUsername(String  username){
        return shiroDao.findByUsername(username);
    }

    public Set<String> findRolesString(String username){
        Set<SysRole> roles = shiroDao.findRoles(username);

        Set<String> result = new HashSet<>();

        for (SysRole r : roles) {
            result.add(r.getRole());
        }

        return result;


    }
    public Set<String> findPermissionsString(String username){
        Set<SysResource> permissions = shiroDao.findPermissions(username);

        Set<String> result = new HashSet<>();

        for (SysResource r : permissions) {
            result.add(r.getPermission());
        }

        return result;
    }

    public int addUser(SysUser sysUser){
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setLocked(Long.parseLong("0"));

        SimpleHash simpleHash = new SimpleHash(
                "md5",
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getUsername()),
                2
        );
        sysUser.setSalt(sysUser.getUsername());
        sysUser.setPassword(simpleHash.toHex());


        return shiroDao.addUser(sysUser);
    }

}
