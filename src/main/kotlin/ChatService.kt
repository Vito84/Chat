package ru.netology

class ChatNotFoundException(message: String) : RuntimeException(message)
class MessageNotFoundException(message: String) : RuntimeException(message)


class ChatService {
    private val chats = hashMapOf<List<Int>, Chat>()



    fun getMessage(userId: Int): List<Chat> {
        return chats
            .filter { entry -> entry.key.contains(userId) }
            .values.toList()
    }


    fun getChat(userId: Int, chatId: Int): Chat? {
        return getMessage(userId).find { it.chatId == chatId }
    }


    fun addMessage(chat: Chat, message: Message): Message {
        chats.getOrPut(listOf(chat.chatId)) { Chat(chat.chatId) }
            .apply { message }
        return message
    }

    fun addChat(chat: Chat): Chat {
        chats.put(listOf(chat.userId, chat.fromId), chat)
        return chat
    }


    fun removeChat(chat: Chat): Boolean {
        chats.remove(listOf(chat.chatId,chat.fromId),chat)
        return true
    }


    fun removeMessage(messageId: Int): Boolean {
        chats.remove(listOf(messageId))
        return true
    }

    fun editMessage(chatId: Int,messageId:Int, message: Message): Message {
        chats.remove(listOf(messageId))
        chats.getOrPut(listOf(chatId)) { Chat(chatId) }
            .apply { message }
        return message
    }

    fun editChat(chatId: Int, chat: Chat): Chat? {
        chats.remove(listOf(chatId))
        return chats.getOrPut(listOf(chatId)) { Chat(chatId) }
            .apply { chat }
    }
}