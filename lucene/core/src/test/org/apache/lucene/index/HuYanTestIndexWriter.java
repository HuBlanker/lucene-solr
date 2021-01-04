package org.apache.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.MockAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.LuceneTestCase;

import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * @author pfliu
 * @date 2021/01/03.
 * @brief 测试testInwriter
 */
public class HuYanTestIndexWriter extends LuceneTestCase {

    public void testAddDocument() throws IOException {

        Directory d = FSDirectory.open(FileSystems.getDefault().getPath(
                "/tmp/lucene-test"));

        // 索引写入的配置
        Analyzer analyzer = new StandardAnalyzer();// 分词器
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);

        // 构建用于操作索引的类
        IndexWriter indexWriter = new IndexWriter(d, conf);

        // add one document & close writer
        addDoc(indexWriter);
        indexWriter.close();
    }

    static void addDoc(IndexWriter writer) throws IOException {
        Document doc = new Document();
        doc.add(newTextField("content", "aaa", Field.Store.YES));
        writer.addDocument(doc);
    }
}
