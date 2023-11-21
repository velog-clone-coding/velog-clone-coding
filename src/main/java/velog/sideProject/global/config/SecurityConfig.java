package velog.sideProject.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import velog.sideProject.global.jwt.JwtAccessDeniedHandler;
import velog.sideProject.global.jwt.JwtAuthenticationEntryPoint;
import velog.sideProject.global.jwt.JwtSecurityConfig;
import velog.sideProject.global.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http
//                .csrf().disable()
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // exception handling 할 때 우리가 만든 클래스를 추가
                /**
                 * .exceptionHandling()
                 * .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                 * .accessDeniedHandler(jwtAccessDeniedHandler)
                 */
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                /**
                 * .and()
                 * .headers()
                 * .frameOptions()
                 * .sameOrigin()
                 */
                .headers(headers ->
                        headers.frameOptions(frameOptions ->
                                frameOptions.sameOrigin()
                        )
                )
                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                /**
                 * .and()
                 * .sessionManagement()
                 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 */
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                /**
                 *  .and()
                 *  .authorizeHttpRequests()
                 *  .requestMatchers("/auth/**").permitAll()
                 *  .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요
                 */
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .anyRequest().permitAll()
//                                .requestMatchers("/auth/**").permitAll()
//                                .requestMatchers("/api/email/mailConfirm").permitAll()
//                                .requestMatchers("/api/myPage/**").permitAll()
//                                .anyRequest().authenticated()
                )
                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}