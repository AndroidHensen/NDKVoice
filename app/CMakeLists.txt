cmake_minimum_required(VERSION 3.4.1)


find_library( # Sets the name of the path variable.
              log-lib
              log )

set(distribution_DIR ${CMAKE_SOURCE_DIR}/libs)

add_library( fmod
             SHARED
             IMPORTED )
set_target_properties( fmod
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmod.so )
add_library( fmodL
             SHARED
             IMPORTED )
set_target_properties( fmodL
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so )
add_library( sound
             SHARED
             src/main/cpp/sound.cpp )

include_directories(src/main/cpp/inc)

target_link_libraries( sound fmod fmodL
                       ${log-lib} )
