### delete thumbs.db with mdfind
mdfind "Thumbs.db" | grep "Thumbs.db" | xargs rm