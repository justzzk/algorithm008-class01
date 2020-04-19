学习笔记
#####1. Queue源码分析
1. 类型：interface，是所有队列类型数据结构都要实现的接口，定义了队列的基本操作；
2. 包含的方法:
(1)boolean add(E e):插入元素e到队列中，在不超过capacity的情况下立即执行，成功返回true，失败返回IllegalStateException；
(2)boolean offer(E e):同样是插入元素到队列中，当队列有容量限制时，总体上该方法好于add
(3)E remove():移除队首元素并返回元素值，当队列空时抛出异常NoSuchElementException；
(4)E poll():同样是移除队首元素，与remove的区别是队列空时返回null
(5)E element():返回队首元素但不删除，空则返回NoSuchElementException；
(6)E peek():返回队首元素但不删除，空则返回NoSuchElementException；


#####2. PriorityQueue源码分析
1. Queue的实现，最大的特点是基于传入的比较器，对入队的参数做比较，按照优先级进行排序，使用的数据结构是最小堆，
所以出队元素是优先级最小的元素；直接父类是AbstractQueue；同时顶层接口有Iterable，即可以使用迭代器迭代容器元素；
内部物理结构是元素位Object（范型）数组，默认初始大小是11，并用size保存实际元素数量；
2. 构造函数：可传入的参数用以构造的参数类型主要有（1）int initialCapacity，定义初始大小，不传入则11；（2）Comparator<? super E> comparator
比较器，定义比较依据；（3）Collection<? extends E> c，传入一个包含数据的容器，构造一个优先队列；（4）PriorityQueue<? extends E> c
以传入的优先队列构造新队列；（5）SortedSet<? extends E> c，以传入的排序集合为参数构造优先队列：当传入的参数是集合类型时，除了传入优先队列为参数
不需要再进行整理，传入其他容器都需要进行heapify()，将所有元素最小堆化，堆化调用的函数主要是siftUp和siftDown，这方面主要是堆的构造过程。
3. 堆的扩容过程：相关的主要成员变量主要是private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
在传入新参数造成数组大小不足时，将调用grow函数，在该函数中，当原本容量小于64，则扩容双倍，当大于64，则增加50%；如果扩容之后的
容量大于MAX_ARRAY_SIZE，就会调用hugeCapacity函数处理；在该函数中，如果grow的参数已经小于0，即已经发生了溢出则抛出OOM异常；如果还没有溢出但是
大于了MAX_ARRAY_SIZE，则新容量为Integer.MAX_VALUE，否则为MAX_ARRAY_SIZE。
4. remove()相关函数：不传入参数时默认移除堆顶元素，当传入一个具体的元素时，将移除该元素并对堆进行调整；还可以移除特定索引的元素，调用
removeAt()，remove具体元素时也是先找到该元素的索引，再调用removeAt()；此外还可以移除或者保留满足某些条件的元素，
比如移除传入的容器中包含的removeAll，或者保留传入的容器中包含的retainAll；



