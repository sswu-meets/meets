package com.sswu.meets.service;

import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto) {
        return userRepository.save(userSaveRequestDto.toEntity()).getUserNo();
    }

    // 결과로 넘어온 User의 Stream을 map을 통해 UserResponseDto 변환, 그 후 List로 반환
    @Transactional
    public List<UserResponseDto> getUserList() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean update(Long user_no, UserUpdateRequestDto userSaveRequestDto) {
        try{
            userRepository.save(userSaveRequestDto.toEntity());
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("error: " + e);
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(Long user_no) {
        User user = userRepository.getById(user_no);  //getById:프록시만 반환
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return false;
        }
    }
}
