package ua.goit.offline.chat.dao;

import ua.goit.offline.chat.entity.BadWord;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
public interface BadWordsDao {
    BadWord get(String word);
}
