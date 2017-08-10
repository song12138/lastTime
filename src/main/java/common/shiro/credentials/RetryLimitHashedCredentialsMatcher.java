package common.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;


import java.util.concurrent.atomic.AtomicInteger;

/**凭证管理器
 * Created by paul on 2017/8/9.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String,AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName= (String) token.getPrincipal();

        //线程安全integer 缓存获取次数
        AtomicInteger tryCount = passwordRetryCache.get(userName);
        if(null == tryCount){
            tryCount = new AtomicInteger(0);
            passwordRetryCache.put(userName, tryCount);
        }

        //++i
        if(tryCount.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException();
        }

        boolean match=super.doCredentialsMatch(token, info);
        if(match){
            passwordRetryCache.remove(userName);
        }
        return match;
    }
}
