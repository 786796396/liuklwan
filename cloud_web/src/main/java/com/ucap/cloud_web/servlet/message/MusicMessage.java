package com.ucap.cloud_web.servlet.message;

/**
 * 音乐消息
 * 
 * @author lixuxiang
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}