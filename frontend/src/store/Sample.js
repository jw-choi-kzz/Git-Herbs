import { create } from "zustand";

export const useSampleStore = create((set)=>({
    sampleList: [],
    addItem: (item) => 
        set((prev)=>({
            sampleList: [...prev.sampleList, 
                {
                    content: item,
                    id: new Date().getMilliseconds() + item
                },
            ],
        })),
}));